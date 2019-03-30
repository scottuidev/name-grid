package com.scottuidev;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        int length = 5;
        try {
            Main main = new Main();
            List<String> names = main.readFile();
            main.runNameGridFinder(names, 5);
        } catch (Exception e) {
            System.out.println("things went bad: " +  e.getMessage());
            e.printStackTrace();
        }

    }

    private void runNameGridFinder(List<String> names, int length) {
        // time how long it takes to run
        long start = System.currentTimeMillis();
        System.out.println("Start " + new Date(start));

        SortedSet<String> namesCleanedUp = this.clean(names, length);
        NameGridFinder nameGridFinder = new NameGridFinder();
        List<NameGrid> nameGrids = nameGridFinder.findNameGrids(namesCleanedUp, length);

        long end = System.currentTimeMillis();

        System.out.println("Unique names: " + namesCleanedUp.size() + "\n");
        for (NameGrid nameGrid : nameGrids) {
            System.out.println(nameGrid);
        }

        System.out.println("End " + new Date(end));
        System.out.println("Count: " + nameGrids.size());
        System.out.println("Total run time: " + (end - start) );
    }

    private List<String> readFile() throws IOException {
        String fileName = "names.txt";
        Path path = Paths.get(fileName);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        return allLines;
    }

    private SortedSet<String> clean(List<String> names, int length) {
        // filter strings to the length and change to lower case
        List<String> namesLowercase = names.stream()
                .parallel()
                .filter(str -> (str.length() == length))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        // dedup
        SortedSet<String> deduped = new TreeSet<>(namesLowercase);
        return deduped;
    }
}
