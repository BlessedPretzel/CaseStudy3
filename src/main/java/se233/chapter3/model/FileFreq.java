package se233.chapter3.model;

public class FileFreq {
    private String name;
    private String path;
    private int freq;
    public FileFreq(String name, String path, int freq) {
        this.name = name;
        this.path = path;
        this.freq = freq;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return String.format("{%s:%d}",name,freq);
    }
}
