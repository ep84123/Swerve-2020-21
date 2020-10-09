package edu.greenblitz.bigRodika.commands;

import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.bigRodika.exceptions.MotorPowerOutOfRangeException;
import edu.greenblitz.bigRodika.subsystems.Chassis;
import edu.greenblitz.bigRodika.subsystems.SwerveModule;
import edu.greenblitz.gblib.command.GBCommand;
import edu.greenblitz.gblib.hid.SmartJoystick;

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

        try {
            for (SwerveModule swerveModule : chassis.getSwerveModules()){
                swerveModule.setAngle(angle);
            }
            chassis.moveRotationMotors(new double[]{rotationPower, rotationPower, rotationPower, rotationPower});
            orelMethod(linearVelocity, angularVelocity);
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

    public void orelMethod(double linearVel, double angularVel){
        
    }
}
