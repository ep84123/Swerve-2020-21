package edu.greenblitz.bigRodika.utils;

public class DirectionHandler<T extends AbstractDH> {

    private T dh;

    public void init(T newT) { // Give the args for the DH in newT
        dh = newT;
    }

    public double handle(double angle, double linVel) {
        if (angle == 0) return angle;
        double linPairedAngle = angle > 0 ? angle : 180 + angle;
        /*
        if the driver will go left the angle will be negative
        but it doesn't matter for the calculation so I converted it to its right equivalent.
         */
        boolean ret = dh.handle(linPairedAngle, linVel);

        return ret ? angle : 180 - angle;
    }


}
