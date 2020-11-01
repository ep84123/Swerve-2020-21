package edu.greenblitz.bigRodika.utils;

public class SimpleDH implements IDirectionHandler{

    private final double DRIVER_INPUT_SCALE = 320;

    @Override
    public boolean shouldGoInverted(double angle, double linVel, double driverInput) {
        driverInput = driverInput*DRIVER_INPUT_SCALE; //rescaling driver input to the scale we want
        double val = ftv(angle, linVel, driverInput);
        return val < 0; // if the value is negative we want to drive inverted
    }

    public static double ftv(double t, double v, double a){
        return Math.signum(180 - 2*t) * (Math.pow(Math.abs(t-90), 1.5 )) + a*v;
    }

}
