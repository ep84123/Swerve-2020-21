package edu.greenblitz.bigRodika.utils;

public class DirectionHandler {
    /***
     * @author Orel
     * @param robotVel - the current velocity of the robot
     * @param  maxVle - the Maximal veocity of the robot
     * @param currAngle - the current angle of the robot
     * @param  destAngle - the andle the robot want to return to
     * @return - returns true for rotating the wheels "backwards"
     *           returns false for rotating the wheels "forwards"
     *           returns null for not rotating the wheels
     */

    private static double maxRatio = 0.5; // if the robot is over this*100% vel it will turn th wheels regularly
    public static boolean goOtherDirection(double robotVel, double maxVel, double currAngle, double destAngle){
        double delta = destAngle - currAngle;
        if(delta == 0 || currAngle/maxVel > maxRatio){
            return false;
        }
        else return (!(delta <= 180) || !(delta >= 0)) && (!(delta <= -180) || !(delta >= -360));
    }
}
