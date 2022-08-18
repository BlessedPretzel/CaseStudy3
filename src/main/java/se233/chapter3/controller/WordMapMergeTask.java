package se233.chapter3.controller;

import se233.chapter3.model.FileFreq;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WordMapMergeTask implements Callable<LinkedHashMap<String, ArrayList<FileFreq>>> {
    private Map<String, FileFreq>[] wordMap;

    public WordMapMergeTask(Map<String, FileFreq>[] wordMap) {
        this.wordMap = wordMap;
    }
    public LinkedHashMap<String, ArrayList<FileFreq>> call() {
        LinkedHashMap<String, ArrayList<FileFreq>> uniqueSets;
        List<Map<String, FileFreq>> wordMapList = new ArrayList<>(Arrays.asList(wordMap));
        uniqueSets = wordMapList.stream()
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(
                        e -> e.getKey(),
                        Collector.of(
                                () -> new ArrayList<FileFreq>(),
                                (list, item) -> list.add(item.getValue()),
                                (current_list, new_items) -> {
                                    current_list.addAll(new_items);
                                    return current_list;
                                }
                        )
                ))
                .entrySet()
                .stream()
                // EXERCISE 1
                .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(value -> {
                    int total = 0;
                    for (FileFreq f : value) {
                        total-=f.getFreq();
                    }
                    return total;
                })))
                // EXERCISE 2
                .collect(Collectors.toMap(map -> {
                    String addition = " (";
                    for (int i = 0; i< map.getValue().size(); i++) {
                        addition+=map.getValue().get(i).getFreq();
                        if (i == map.getValue().size()-1) {
                            addition+=")";
                        } else {
                            addition+=",";
                        }
                    }
                    return map.getKey()+addition;
                }
                , Map.Entry::getValue, (fileFreqs, fileFreqs2) -> fileFreqs, LinkedHashMap::new));
        return uniqueSets;
    }

}
