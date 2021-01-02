
package edu.greenblitz.bigRodika.utils;

public class DumbDH extends AbstractDH {


    public DumbDH() {
    }

    /**
     *
     * @param angle - the angle of the turn
     * @param linVel - the velocity of th robot
     * @return if angle grater then 180.
     */
    @Override
    public boolean handle(double angle, double linVel) {
        return angle > 180;
    }
}
