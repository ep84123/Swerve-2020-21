package edu.greenblitz.bigRodika.utils;

import edu.greenblitz.bigRodika.Basics.Pair;

public class DirectionHandler<T extends AbstractDH> {

    private T dh;

    public DirectionHandler(T newT) { // Give the args for the DH in newT
        dh = newT;
    }

    /**
     *
     * @param angle - the angle of the robot
     * @param linVel - the linear velocity of the robobt
     * @return
     *      boolean - power or negative power
     *      double - the new angle
     */
    public Pair<Boolean, Double> handle(double angle, double linVel) {
        if (angle == 0) return new Pair(true, 0.0) ;
        double linPairedAngle = angle > 0 ? angle : 180 + angle;
        /*
        if the driver will go left the angle will be negative
        but it doesn't matter for the calculation so I converted it to its right equivalent.
         */
        boolean ret = dh.handle(linPairedAngle, linVel);

        return new Pair(ret, ret ? angle : 180 - angle);
    }


}
