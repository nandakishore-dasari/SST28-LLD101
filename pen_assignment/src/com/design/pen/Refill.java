package com.design.pen;

public class Refill {
    private Ink ink;
    private Nib nib;

    public Refill(Ink ink, Nib nib) {
        this.ink = ink;
        this.nib = nib;
    }

    public void flowInk() {
        System.out.println("Ink is flowing in " + ink.getColor() + " color.");
    }
}