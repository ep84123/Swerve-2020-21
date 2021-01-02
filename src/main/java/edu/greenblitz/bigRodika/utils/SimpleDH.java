package edu.greenblitz.bigRodika.utils;

/**
 * @author Orel
 * @brain Peleg, Itgil, Orel
 */

public class SimpleDH extends AbstractDH{

    private final double DRIVER_INPUT_SCALE = 320;
    private double driverInput;
    private double pow = 1.5; //setting a default pow

    public SimpleDH(double driverInput, double pow) {
        this.driverInput = driverInput;
        this.pow = pow;
    }

    public SimpleDH(double driverInput) {
        this.driverInput = driverInput;
    }

    /**
     * assuming that (extend comment for full view):
     * @param args:
     *            arg[0] - driver input
     *            arg[1] - pow
     */
    public SimpleDH(double ... args){ // Ctor for wrapper
        this.driverInput = args[0];
        this.pow = args[1];
    }

    @Override
    public boolean handle(double angle, double linVel) {
        double val = ftv(angle, linVel, this.driverInput, this.pow);
        return val <= 0; // if the value is negative we want to drive inverted
    }

    /**
     *
     * @param t - is the
     * @param v - is
     * @param a
     * @param p
     * @return
     */
    public static double ftv(double t, double v, double a, double p ){
        //the function that calculates if should go inverted
        return Math.signum(180 - 2*t) * (Math.pow(Math.abs(t-90), p )) + a*Math.abs(v);
    }

}
