package edu.greenblitz.bigRodika.commands;

import edu.greenblitz.bigRodika.RobotMap;


/**
 * @author Orel
 */

public class ArcDrive {

    private static final double dist = RobotMap.Limbo2.Measures.WHEEL_DIST_FROM_CENTER;

    // TODO: 14/10/2020 change method name
    public static double[] arcPowers(double robotLinVel, double robotAngVel, double robotAngle) {
        double robotR = robotLinVel / robotAngVel;
        double maxR = robotR + dist;
        double maxVel = RobotMap.Limbo2.Chassis.MAX_LINEAR_VELOCITY * maxR / robotR;

        return calcVelocities(robotAngle, robotR, maxR, maxVel);
    }

    private static double calcVel(double angle, double robotR, double maxVel, double maxR){
         double R = Math.sqrt(Math.pow(robotR, 2) + Math.pow(dist, 2) - 2 * Math.cos(angle));
         return maxVel * R / maxR;
    }

    public static double[] calcVelocities(double robotAngle, double robotR, double maxR, double maxVel){
        double lengthAngle = Math.acos(1- (Math.pow(RobotMap.Limbo2.Measures.LENGTH, 2) / 2 * Math.pow(dist, 2)));
        double widthAngle = 180 - lengthAngle;

        double tlAngle = lengthAngle / 2 + robotAngle; // assuming the wheels turn clockwise
        double trAngle = tlAngle + widthAngle;
        double blAngle = tlAngle - lengthAngle;
        double brAngle = tlAngle - widthAngle - lengthAngle;

        // top-left, top-right, bottom-left, bottom-right
        double tl = calcVel(tlAngle, robotR, maxR, maxVel), tr = calcVel(trAngle, robotR, maxR, maxVel),
                bl = calcVel(blAngle, robotR, maxR, maxVel), br = calcVel(brAngle, robotR, maxR, maxVel);

        return new double[] {tr, tl, bl, br};
    }

}
