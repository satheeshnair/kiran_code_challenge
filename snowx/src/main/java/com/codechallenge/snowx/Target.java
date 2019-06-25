package com.codechallenge.snowx;

public class Target {

    private String type;
    private int x_axis;
    private int y_axis;

    private double confidenceLevel;

    public Target(String type, int x_axis, int y_axis, double confidenceLevel) {
        this.type = type;
        this.x_axis = x_axis;
        this.y_axis = y_axis;
        this.confidenceLevel = confidenceLevel;
    }

    public String getType() {
        return type;
    }

    public Target setType(String type) {
        this.type = type;
        return this;
    }

    public int getX_axis() {
        return x_axis;
    }

    public Target setX_axis(int x_axis) {
        this.x_axis = x_axis;
        return this;
    }

    public int getY_axis() {
        return y_axis;
    }

    public Target setY_axis(int y_axis) {
        this.y_axis = y_axis;
        return this;
    }

    public double getConfidenceLevel() {
        return confidenceLevel;
    }

    public Target setConfidenceLevel(double confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
        return this;
    }

    @Override
    public String toString() {
        return "Target{" +
                "type='" + type + '\'' +
                ", x_axis=" + x_axis +
                ", y_axis=" + y_axis +
                ", confidenceLevel=" + confidenceLevel +
                '}';
    }
}
