package com.scottuidev;

import java.util.*;
import java.util.stream.Collectors;

public class NameCubeFinder {

    private List<NameCube> nameCubes;

    public void findNameCubes(List<String> names, int length) {

        //reset nameCubes
        nameCubes = new ArrayList<>();

        long start = System.currentTimeMillis();
        System.out.println("Start " + new Date(start));

        SortedSet<String> namesCleanedUp = clean(names, length);

        for(String name : namesCleanedUp) {
            findNameCube(name, namesCleanedUp, length);
        }

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
        List<String> namesLowercase = names.stream().parallel()
                .map(String::toLowerCase)
                .filter(str -> (str.length() == length))
                .collect(Collectors.toList());
        // dedup
        SortedSet<String> deduped = new TreeSet<>(namesLowercase);
        return deduped;
    }

    private void findNameCube(String name, SortedSet<String> names, int length) {

        NameCube cube = new NameCube(length);
        cube.addName(name, 0);
        recursiveFindNameCube(cube, names, 1, length);

    }

    private void recursiveFindNameCube(NameCube cube, SortedSet<String> names, int index, int length) {

        // get the filter for the next name to add
        String filter = cube.getFilterForIndex(index);

        // find all names that start with the filter
        List<String> list = names.stream()
                .filter(str -> str.startsWith(filter))
                .collect(Collectors.toList());

        // no names match the filter so give up
        if (list.isEmpty()) {
            return;
        }

        for(String name : list) {
            cube.addName(name, index);
            // recursive call if we still need to fill in more names
            if (index < length - 1) {
                recursiveFindNameCube(cube, names, index + 1, length);
            } else {
                // added the last name so this is a name cube, add it to the list of found cubes
                nameCubes.add(new NameCube(cube));
            }
        }
    }
}
