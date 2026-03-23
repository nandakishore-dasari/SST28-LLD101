package com.design.pen;
public abstract class Pen {
    private String name;
    private String brand;
    private PenType type;

    public Pen(String name, String brand, PenType type) {
        this.name = name;
        this.brand = brand;
        this.type = type;
    }

    public abstract void write();

    // Getters
    public String getName() { return name; }
}