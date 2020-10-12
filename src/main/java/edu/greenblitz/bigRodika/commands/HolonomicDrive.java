package edu.greenblitz.bigRodika.commands;

import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.bigRodika.exceptions.MotorPowerOutOfRangeException;
import edu.greenblitz.bigRodika.subsystems.Chassis;
import edu.greenblitz.gblib.command.GBCommand;
import edu.greenblitz.gblib.hid.SmartJoystick;

/**
 * @quthor Orel
 */

public class HolonomicDrive extends GBCommand {

    private final SmartJoystick joystick;
    private final Chassis chassis;
    private final double POWER_CONST = 1.0;
    public HolonomicDrive(SmartJoystick joystick){
        this.joystick = joystick;
        chassis = Chassis.getInstance();
    }

    @Override
    public void initialize(){
        chassis.toBrake();
    }

    @Override
    public void execute() {
        double power = getLinearPower();
        double angle = getDriveAngle();
        try {
            chassis.moveMotors(new double[]{power, power, power, power}, new double[]{angle, angle, angle, angle});
        } catch (MotorPowerOutOfRangeException e) {
            e.printStackTrace();
        }
    }

    public double getLinearPower(){
        double xVal = joystick.getAxisValue(SmartJoystick.Axis.LEFT_X);
        double yVAl = joystick.getAxisValue(SmartJoystick.Axis.LEFT_Y);
        return Math.sqrt(xVal*xVal + yVAl*yVAl);
    }

    public double getDriveAngle(){
        double xVal = joystick.getAxisValue(SmartJoystick.Axis.LEFT_X);
        double yVAl = joystick.getAxisValue(SmartJoystick.Axis.LEFT_Y);
        return Math.atan(yVAl/xVal);
    }

}
