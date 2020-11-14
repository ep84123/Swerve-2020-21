
package edu.greenblitz.bigRodika.utils;

public class DumbDH extends AbstractDH {
    public DumbDH(double... args) {
        //super(args);
    }

    @Override
    public boolean handle(double angle, double linVel) {
        return angle <= 90 && linVel <= 0;
    }
}
