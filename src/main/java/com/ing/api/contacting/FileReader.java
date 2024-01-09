package com.ing.api.contacting;

import org.javatuples.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

public class FileReader {
    private final String filename;
    private final String keyValueSeparator;
    public FileReader(String filename, String keyValueSeparator) {
        this.filename = filename;
        this.keyValueSeparator = keyValueSeparator;
    }

    public FileReader(String filename) {
        this(filename, ":");
    }

    public List<Pair<String, String>> getEntries() {
        var result = new ArrayList<Pair<String, String>>();
        for (String line: getLines(filename)) {
            String[] separated = line.split(keyValueSeparator);
            result.add(new Pair<>(separated[0].trim(), separated[1].trim()));
        }
        return result;
    }
    private List<String> getLines(String filename) {
        List<String> result = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            result = lines.collect(Collectors.toList());
        } catch (IOException e) {
            out.println("file " + filename + " not found!");

        }
        return result;
    }
}
