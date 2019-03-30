package com.scottuidev;

public class NameCube {

    private int length;
    private String[] cube;

    public NameCube(int length) {
        this.length = length;
        this.cube = new String[length];
    }

    public NameCube(NameCube cubeToClone) {
        this(cubeToClone.length);

        for(int i = 0; i < this.length; i++) {
            this.addName(cubeToClone.cube[i], i);
        }
    }

    public void addName(String name, int index) {
        this.cube[index] = name;
    }

    public String getFilterForIndex(int index) {
        StringBuilder filterStrBuilder = new StringBuilder();
        for(int i = 0; i < index; i++) {
            filterStrBuilder.append(this.cube[i].substring(index, index + 1));
        }

        return filterStrBuilder.toString();
    }

    @Override
    public String toString() {
        String out = "";
        for(int i = 0; i < this.length; i++) {
            out += this.cube[i] + "\n";
        }

        return out;
    }
}
