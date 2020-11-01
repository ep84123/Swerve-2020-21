package edu.greenblitz.bigRodika.utils;

public interface IDirectionHandler {
    /**\
     *
     * @param angle - the angle of the robot in degrees as long as not mentioned otherwise
     * @param linVel - the linear velocity of the robobt
     * @param driverInput - how much the driver wanted to be corrected by the function (a number between 0 to 1 if not mentioned otherwise)
     * @return  - if the robot should go inverted
     */
    boolean shouldGoInverted(double angle, double linVel, double driverInput);
}
