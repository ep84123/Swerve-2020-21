package edu.greenblitz.bigRodika.utils;

public class StupidDH implements IDriveHandler {
    /**
     *
     * @param linVel - Linear Velocity of the robot
     * @param angle - the angle the driver wants to go to (assuming it's robot oriented)
     * @param driverInput - the input of the driver, here will be treated as a boolean
     * @return - if the angle is grater than 180 and driver asked our recommendation return true
     */
    @Override
    public boolean shouldGoBackwards(double linVel, double angle, double driverInput) {
        return driverInput == 1 && angle > 180;
    }

}