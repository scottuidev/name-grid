package com.scottuidev;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            Main main = new Main();
            List<String> names = main.readFile();
            NameCubeFinder nameCubeFinder = new NameCubeFinder();
            nameCubeFinder.findNameCubes(names, 5);
        } catch (Exception e) {
            System.out.println("things went bad: " +  e.getMessage());
            e.printStackTrace();
        }


    }

    private List<String> readFile() throws IOException {

        String fileName = "names.txt";
        Path path = Paths.get(fileName);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        return allLines;

    }
}
