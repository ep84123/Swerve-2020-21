package edu.greenblitz.bigRodika.commands;

import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.bigRodika.exceptions.MotorPowerOutOfRangeException;
import edu.greenblitz.bigRodika.subsystems.Chassis;
import edu.greenblitz.bigRodika.subsystems.SwerveModule;
import edu.greenblitz.bigRodika.commands.ArcDrive;
import edu.greenblitz.gblib.command.GBCommand;
import edu.greenblitz.gblib.hid.SmartJoystick;

/**
 * @author Itgil
 */

public class FullDrive extends GBCommand {
    private final SmartJoystick mainJoystick;
    private final Chassis chassis;
    private final double POWER_CONST = 1.0;

    public FullDrive(SmartJoystick mainJoystick){
        this.mainJoystick = mainJoystick;
        chassis = Chassis.getInstance();
    }

    @Override
    public void initialize(){
        chassis.toBrake();
    }

    @Override
    public void execute() {
        double linearVelocity = getLinearVel();
        double angularVelocity = getAngularVel();
        double angle = getDriveAngle();

        double rotationPower = getRotationPower(angularVelocity);
        double[] rotationPowers = {rotationPower, rotationPower, rotationPower, rotationPower};
        double[] drivePowers = ArcDrive.arcPowers(linearVelocity, angularVelocity, angle); // wrong, arcPowers doesn't return powers

        try {
            chassis.moveMotors(new double[]{0, 0, 0, 0}, new double[]{angle, angle, angle, angle}, true);
            chassis.moveRotationMotors(rotationPowers);
            chassis.moveDriveMotors(drivePowers);
        } catch (MotorPowerOutOfRangeException e) {
            e.printStackTrace();
        }
    }

    public double getLinearVel(){
        double xVal = mainJoystick.getAxisValue(SmartJoystick.Axis.LEFT_X);
        double yVAl = mainJoystick.getAxisValue(SmartJoystick.Axis.LEFT_Y);
        return Math.sqrt(xVal*xVal + yVAl*yVAl) * RobotMap.Limbo2.Chassis.MAX_LINEAR_VELOCITY;
    }

    public double getAngularVel(){
        double sideXVal = mainJoystick.getAxisValue(SmartJoystick.Axis.RIGHT_X);
        return sideXVal * RobotMap.Limbo2.Chassis.MAX_ANGULAR_VELOCITY;
    }

    public double getDriveAngle(){
        double xVal = mainJoystick.getAxisValue(SmartJoystick.Axis.LEFT_X);
        double yVAl = mainJoystick.getAxisValue(SmartJoystick.Axis.LEFT_Y);
        return Math.atan(yVAl/xVal);
    }

    public double getRotationPower(double angularVel){
        return angularVel * RobotMap.Limbo2.Chassis.MAX_ANGULAR_VELOCITY;
    }
}
