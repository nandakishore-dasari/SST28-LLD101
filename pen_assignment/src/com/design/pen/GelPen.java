package com.design.pen;

public class GelPen extends Pen implements Refillable {
    private Refill refill;

    public GelPen(String name, String brand, Refill refill) {
        super(name, brand, PenType.GEL);
        this.refill = refill;
    }

    @Override
    public void write() {
        System.out.println("Writing with Gel Pen: " + getName());
        refill.flowInk();
    }

    @Override
    public void changeRefill(Refill newRefill) {
        this.refill = newRefill;
        System.out.println("Refill changed for " + getName());
    }
}