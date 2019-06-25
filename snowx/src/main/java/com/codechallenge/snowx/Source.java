package com.codechallenge.snowx;

public class Source {

    private char[][] data;
    private String type;
    private int noOfElements;

    public Source(char[][] data, String type, int noOfElements) {
        this.data = data;
        this.type = type;
        this.noOfElements = noOfElements;
    }

    public char[][] getData() {
        return data;
    }

    public Source setData(char[][] data) {
        this.data = data;
        return this;
    }

    public String getType() {
        return type;
    }

    public Source setType(String type) {
        this.type = type;
        return this;
    }

    public int getNoOfElements() {
        return noOfElements;
    }

    public Source setNoOfElements(int noOfElements) {
        this.noOfElements = noOfElements;
        return this;
    }
}
