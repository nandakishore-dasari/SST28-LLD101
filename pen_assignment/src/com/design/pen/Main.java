package com.design.pen;

public class Main {
    public static void main(String[] args) {
        //Create parts
        Ink blueInk = new Ink("Blue", "Parker");
        Nib fineNib = new Nib(0.5);
        Refill myRefill = new Refill(blueInk, fineNib);

        //Create Pen
        GelPen myPen = new GelPen("Pilot G2", "Pilot", myRefill);

        //Use Pen
        myPen.write();

        //Refill it
        Refill newRefill = new Refill(new Ink("Black", "Parker"), fineNib);
        myPen.changeRefill(newRefill);
        myPen.write();
    }
}