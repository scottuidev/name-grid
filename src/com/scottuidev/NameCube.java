package com.scottuidev;

public class NameCube {

    private int length;
//    private char[][] cube;
    private String[] cube;

    public NameCube(int length) {
        this.length = length;
//        this.cube = new char[length][length];
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


//    public void addName(String name, int index) {
//        char[] strAtIdx = this.cube[index];
//        char[] nameCharArray = name.toCharArray();
//        for(int i = 0; i < strAtIdx.length; i++) {
//            strAtIdx[i] = nameCharArray[i];
//            this.cube[i][index] = nameCharArray[i];
//        }
//    }

    public String getFilterForIndex(int index) {
        // 1, get 0,0
        // 2, 0,1 and 1,1
        // 3
        String filter = "";
        for(int i = 0; i < index; i++) {
            filter += this.cube[i].substring(index, index + 1);
        }

        return filter;

    }

//    public String getNameAtIndex(int index) {
//        String str = String.valueOf(this.cube[index]);
//        if(str.indexOf('\u0000') != -1) {
//            str = str.substring(0, str.indexOf('\u0000'));
//        }
//        return str;
//    }

//    @Override
//    public String toString() {
//        String out = "";
//        for(int i = 0; i < this.length; i++) {
//            out += String.valueOf(this.cube[i]) + "\n";
//        }
//
//        return out;
//    }

    @Override
    public String toString() {
        String out = "";
        for(int i = 0; i < this.length; i++) {
            out += this.cube[i] + "\n";
        }

        return out;
    }
}
