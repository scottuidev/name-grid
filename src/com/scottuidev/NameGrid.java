package com.scottuidev;

public class NameGrid {

    private int length;
    private String[] grid;

    public NameGrid(int length) {
        this.length = length;
        this.grid = new String[length];
    }

    public NameGrid(NameGrid gridToClone) {
        this(gridToClone.length);

        for(int i = 0; i < this.length; i++) {
            this.addName(gridToClone.grid[i], i);
        }
    }

    public void addName(String name, int index) {
        this.grid[index] = name;
    }

    public String getFilterForIndex(int index) {
        StringBuilder filterStrBuilder = new StringBuilder();
        for(int i = 0; i < index; i++) {
            filterStrBuilder.append(this.grid[i].substring(index, index + 1));
        }

        return filterStrBuilder.toString();
    }

    @Override
    public String toString() {
        String out = "";
        for(int i = 0; i < this.length; i++) {
            out += this.grid[i] + "\n";
        }

        return out;
    }
}
