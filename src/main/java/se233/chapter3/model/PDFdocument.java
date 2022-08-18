package se233.chapter3.model;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PDFdocument {
    private String name;
    private String filePath;

    private PDDocument pdDocument;
    private LinkedHashMap<String, ArrayList<FileFreq>> uniqueSets;
    public PDFdocument(String filePath) throws IOException {
        this.name = Path.of(filePath).getFileName().toString();
        this.filePath = filePath;
        File pdfFile = new File(filePath);
        this.pdDocument = PDDocument.load(pdfFile);
    }

    public String getFilePath() {
        return filePath;
    }

    public LinkedHashMap<String, ArrayList<FileFreq>> getUniqueSets() {
        return uniqueSets;
    }

    public String getName() {
        return name;
    }

    public PDDocument getPdDocument() {
        return pdDocument;
    }
}
