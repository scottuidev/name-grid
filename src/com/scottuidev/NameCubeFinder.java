package com.scottuidev;

import java.util.*;
import java.util.stream.Collectors;

public class NameCubeFinder {

    public void findNameCubes(List<String> names, int length) {

        List<NameCube> nameCubes = new ArrayList<>();

        long start = System.currentTimeMillis();
        System.out.println("Start " + new Date(start));

        SortedSet<String> namesCleanedUp = clean(names, length);

        namesCleanedUp.stream()
//                .parallel()  // getting mixed results trying to parallelize here, can be ~100 millis faster or double time to proc
                .forEach(name -> nameCubes.addAll(findNameCube(name, namesCleanedUp, length)));

        long end = System.currentTimeMillis();

        System.out.println("Unique names: " + namesCleanedUp.size() + "\n");
        for (NameCube cube : nameCubes) {
            System.out.println(cube);
        }

        System.out.println("End " + new Date(end));
        System.out.println("Count: " + nameCubes.size());
        System.out.println("Total run time: " + (end - start) );
    }

    private SortedSet<String> clean(List<String> names, int length) {
        // to lower case
        List<String> namesLowercase = names.stream()
                .parallel()
                .map(String::toLowerCase)
                .filter(str -> (str.length() == length))
                .collect(Collectors.toList());
        // dedup
        SortedSet<String> deduped = new TreeSet<>(namesLowercase);
        return deduped;
    }

    private List<NameCube> findNameCube(String name, SortedSet<String> names, int length) {
        NameCube cube = new NameCube(length);
        cube.addName(name, 0);
        return recursiveFindNameCube(cube, names, 1, length);
    }

    private List<NameCube> recursiveFindNameCube(NameCube cube, SortedSet<String> names, int index, int length) {

        List<NameCube> cubes = new ArrayList<>();

        // get the filter for the next name to add
        String filter = cube.getFilterForIndex(index);

        // find all names that start with the filter
        List<String> filteredNameList = names.stream()
                .filter(str -> str.startsWith(filter))
                .collect(Collectors.toList());

        // go through the list of names that met the filter and see if we can complete the name cube
        for(String name : filteredNameList) {
            cube.addName(name, index);
            // recursive call if we still need to fill in more names
            if (index < length - 1) {
                cubes.addAll(recursiveFindNameCube(cube, names, index + 1, length));
            } else {
                // added the name to the last index so this is a complete name cube, add it to the list of cubes to return
                cubes.add(new NameCube(cube));
            }
        }

        return cubes;
    }
}
