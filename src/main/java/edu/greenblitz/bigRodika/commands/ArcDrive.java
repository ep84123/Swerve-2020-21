package edu.greenblitz.bigRodika.commands;

import edu.greenblitz.bigRodika.RobotMap;

public class ArcDrive {

    private double robotLinVel, robotAngVel, robotAngle;
    private double mainR, maxVel, maxR;
    private double W = RobotMap.Limbo2.Measures.WIDTH,
            L = RobotMap.Limbo2.Measures.LENGTH,
            d = RobotMap.Limbo2.Measures.WHEEL_DIST_FROM_CENTER;

    public ArcDrive(double robotLinVel, double robotAngVel, double robotAngle) {
        this.robotLinVel = robotLinVel;
        this.robotAngVel = robotAngVel;
        this.robotAngle = robotAngle;
        this.mainR = this.robotLinVel/this.robotAngVel;
        this.maxR = this.mainR + d;
    }

    private double calcVel(double angle){
         double R = Math.sqrt(mainR*mainR + d*d - 2*Math.cos(angle));
         return maxVel * R/maxR;
    }


    public double[] calcVelocities(){
        double lengthAngle = Math.acos(1-(L*L / 2*d*d));
        double widthAngle = 180 - lengthAngle;

        double tlAngle = lengthAngle/2 + robotAngle; // assuming the wheels turns clockwise
        double trAngle = tlAngle + widthAngle,
                blAngle = tlAngle - lengthAngle,
                brAngle = tlAngle- widthAngle - lengthAngle;

        // top-left, top-right, bottom-left, bottom-right
        double tl = calcVel(tlAngle),
                tr = calcVel(trAngle),
                bl = calcVel(blAngle),
                br = calcVel(brAngle);

        return new double[] {tl, tr, bl, br};

    }

}
