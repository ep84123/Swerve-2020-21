package edu.greenblitz.bigRodika.utils;

/**
 * @author Orel
 */

public class SimpleDriverFriendlyDH extends SimpleDH {

    private final double MAX_LIN_VEL = 5; //WARNING This is not a measured value!

    /**
     * takes the driver input as an angle and not as number in some random scale
     * @param angle - the angle of the robot in degrees as long as not mentioned otherwise
     * @param linVel - the linear velocity of the robobt
     * @param args - additional arguments
     *             args[0] - driver input, the angle that in max vel the DH will correct him in degrees and between 90 and 180
     *             args[1] - the wanted pow for the function
     * @return
     */
    @Override
    public boolean shouldGoInverted(double angle, double linVel, double... args) {
        return super.shouldGoInverted(angle, linVel, new double[]{a(args[0], args[1]), args[1]});
    }

    /**
     *Calculates the a of the function according to the wanted angle
     * @param angle - the driver input. look at the method above ^.
     * @param pow - the pow. look at the method above ^.
     * @return - the a of the function
     */
    private double a(double angle, double pow){
        return (Math.signum(180-2*angle)*Math.abs(Math.pow(angle-90,pow)))/(-MAX_LIN_VEL);
    }
}
