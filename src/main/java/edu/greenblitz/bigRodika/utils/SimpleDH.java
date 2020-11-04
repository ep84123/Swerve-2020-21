package edu.greenblitz.bigRodika.utils;

public class SimpleDH implements IDirectionHandler{

    private final double DRIVER_INPUT_SCALE = 320;
    private final double DEFAULT_POW = 1.5;
    @Override
    public boolean shouldGoInverted(double angle, double linVel, double driverInput, double ... args) {
        driverInput = driverInput*DRIVER_INPUT_SCALE; //rescaling driver input to the scale we want
        double pow = args[0]; // adding options for the user by making the power to raise the angle
        double val = ftv(angle, linVel, driverInput, pow);
        return val < 0; // if the value is negative we want to drive inverted
    }

    public boolean shouldGoInverted(double angle, double linVel, double driverInput){
        return shouldGoInverted( angle,  linVel,  driverInput, DEFAULT_POW);
    }


    public static double ftv(double t, double v, double a, double p ){
        return Math.signum(180 - 2*t) * (Math.pow(Math.abs(t-90), p )) + a*v;
    }

}
