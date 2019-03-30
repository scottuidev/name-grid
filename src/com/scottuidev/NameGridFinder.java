package com.scottuidev;

import java.util.*;
import java.util.stream.Collectors;

public class NameGridFinder {

    public List<NameGrid> findNameGrids(SortedSet<String> names, int length) {

        List<NameGrid> nameGrids = new ArrayList<>();

        names.stream()
//                .parallel()  // getting mixed results trying to parallelize here, can be ~100 millis faster or double time to proc
                .forEach(name -> {
                    NameGrid nameGrid = new NameGrid(length);
                    nameGrid.addName(name, 0);
                    nameGrids.addAll(recursiveFindNameGrid(nameGrid, names, 1, length));
                });

        return nameGrids;
    }

    private List<NameGrid> recursiveFindNameGrid(NameGrid nameGrid, SortedSet<String> names, int index, int length) {

        List<NameGrid> nameGrids = new ArrayList<>();

        // get the filter for the next name to add
        String filter = nameGrid.getFilterForIndex(index);

        // find all names that start with the filter
        List<String> filteredNameList = names.stream()
                .filter(str -> str.startsWith(filter))
                .collect(Collectors.toList());

        // go through the list of names that met the filter and see if we can complete the name grid
        for(String name : filteredNameList) {
            nameGrid.addName(name, index);
            // recursive call if we still need to fill in more names
            if (index < length - 1) {
                nameGrids.addAll(recursiveFindNameGrid(nameGrid, names, index + 1, length));
            } else {
                // added the name to the last index so this is a complete name grid, add it to the list of cubes to return
                nameGrids.add(new NameGrid(nameGrid));
            }
        }

        return nameGrids;
    }
}
