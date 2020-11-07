package edu.greenblitz.bigRodika.utils;

/**
 * @author Orel
 * @brain Peleg, Itgil, Orel
 */

public class SimpleDH implements IDirectionHandler{

    private final double DRIVER_INPUT_SCALE = 320;
    private final double DEFAULT_POW = 1.5;

    /**
     * Uses a mathematical function to decide what to do
     * @param angle - the angle of the robot in degrees as long as not mentioned otherwise
     * @param linVel - the linear velocity of the robot
     * @param args - the 1st arg in args is the wanted pow
     * @return
     */
    @Override
    public boolean handle(double angle, double linVel, double ... args) {
        double driverInput = args[0]*DRIVER_INPUT_SCALE; //rescaling driver input to the scale we want
        double pow = args[1]; // adding options for the user by making the power to raise the angle
        double val = ftv(angle, linVel, driverInput, pow);
        return val <= 0 && linVel < 0; // if the value is negative we want to drive inverted
    }

    /**
     * Wrapper for the other shouldGoInverted method that takes less parameters
     */
    public boolean handle(double angle, double linVel, double driverInput){
        return handle( angle,  linVel, new double[]{driverInput, DEFAULT_POW});
    }

    /**
     * the function itself
     * @param t
     * @param v
     * @param a
     * @param p
     * @return
     */
    public static double ftv(double t, double v, double a, double p ){
        return Math.signum(180 - 2*t) * (Math.pow(Math.abs(t-90), p )) + a*Math.abs(v);
    }

}
