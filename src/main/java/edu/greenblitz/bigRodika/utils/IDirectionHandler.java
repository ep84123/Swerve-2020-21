package edu.greenblitz.bigRodika.utils;

/**
 * @author Orel
 */

public interface IDirectionHandler {
    /**\
     *
     * @param angle - the angle of the robot in degrees as long as not mentioned otherwise
     * @param linVel - the linear velocity of the robobt
     * @param args - more arguments
     * @return - the absolute direction of the recommended linear vel
     */
    boolean shouldGoInverted(double angle, double linVel, double ... args);
}
