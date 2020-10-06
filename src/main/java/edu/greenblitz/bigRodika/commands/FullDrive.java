package edu.greenblitz.bigRodika.commands;

import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.bigRodika.exceptions.MotorPowerOutOfRangeException;
import edu.greenblitz.bigRodika.subsystems.Chassis;
import edu.greenblitz.gblib.command.GBCommand;
import edu.greenblitz.gblib.hid.SmartJoystick;

public class FullDrive extends GBCommand {
    private final SmartJoystick mainJoystick;
    private final SmartJoystick sideJoystick;
    private final Chassis chassis;
    private final double POWER_CONST = 1.0;

    public FullDrive(SmartJoystick mainJoystick, SmartJoystick sideJoystick){
        this.mainJoystick = mainJoystick;
        this.sideJoystick = sideJoystick;
        chassis = Chassis.getInstance();
    }

    @Override
    public void initialize(){
        chassis.toBrake();
    }

    @Override
    public void execute() {
//        double xVal = joystick.getAxisValue(SmartJoystick.Axis.RIGHT_X);
//        double yVAl = joystick.getAxisValue(SmartJoystick.Axis.RIGHT_Y);
//        double power = Math.sqrt(xVal*xVal + yVAl*yVAl);
//        double angle = Math.atan(yVAl/xVal);
        double sideXVal = sideJoystick.getAxisValue(SmartJoystick.Axis.RIGHT_X);
        double rotationPower = -sideXVal * RobotMap.Limbo2.Chassis.ROTATION_KV;

        try {
            chassis.moveRotationMotors(new double[]{rotationPower, rotationPower, rotationPower, rotationPower});
        } catch (MotorPowerOutOfRangeException e) {
            e.printStackTrace();
        }
    }
}
