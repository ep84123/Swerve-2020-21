package edu.greenblitz.bigRodika.utils;

public abstract class AbstractDH implements IDirectionHandler {

    public AbstractDH(double... args) {
        System.out.println("Abstract Ctor Works");
    }

    @Override
    public abstract boolean handle(double angle, double linVel);

}
