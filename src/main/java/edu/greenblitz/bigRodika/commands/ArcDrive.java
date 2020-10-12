package edu.greenblitz.bigRodika.commands;

import edu.greenblitz.bigRodika.RobotMap;

public class ArcDrive {

    private double robotLinVel, robotAngVel, robotAngle;
    private double R, maxVel, maxRad;

    public ArcDrive(double robotLinVel, double robotAngVel, double robotAngle) {
        this.robotLinVel = robotLinVel;
        this.robotAngVel = robotAngVel;
        this.robotAngle = robotAngle;
        this.R = this.robotLinVel/this.robotAngVel;
        this.maxRad = this.R + RobotMap.Limbo2.
    }
}
