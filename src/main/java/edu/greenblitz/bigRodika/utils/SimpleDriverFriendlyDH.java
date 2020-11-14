package edu.greenblitz.bigRodika.utils;

/**
 * @author Orel
 */

public class SimpleDriverFriendlyDH extends SimpleDH {

    private final double MAX_LIN_VEL = 5; /*FixMe: the max speed isn't measured yet*/

    public SimpleDriverFriendlyDH(double driverInput, double pow) { //Ctor that gets all params
        super(driverInput, pow);
    }

    public SimpleDriverFriendlyDH(double driverInput) { // Ctor that uses default pow
        super(driverInput);
    }

    /**
     * assuming that (extend comment for full view):
     * @param args:
     *            arg[0] - driver input
     *            arg[1] - pow
     */
    public SimpleDriverFriendlyDH(double ... args) { //Ctor fot the wrapper
        super(args[0], args[1]);
    }

    @Override
    public boolean handle(double angle, double linVel) {
        return super.handle(angle, linVel);
    }

    private double a(double angle, double pow){
        return (Math.signum(180-2*angle)*Math.abs(Math.pow(angle-90,pow)))/(-MAX_LIN_VEL);
    }
}
