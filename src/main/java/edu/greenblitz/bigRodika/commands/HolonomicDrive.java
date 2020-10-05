package edu.greenblitz.bigRodika.commands;

import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.bigRodika.exceptions.MotorPowerOutOfRangeException;
import edu.greenblitz.bigRodika.subsystems.Chassis;
import edu.greenblitz.gblib.command.GBCommand;
import edu.greenblitz.gblib.hid.SmartJoystick;

public class HolonomicDrive extends GBCommand {

    private SmartJoystick joystick;
    private Chassis chassis;
    private final double POWER_CONST = 1.0;
    public HolonomicDrive(SmartJoystick joystick){
        this.joystick = joystick;
        chassis = Chassis.getInstance();
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute() {
        double xVal = joystick.getAxisValue(SmartJoystick.Axis.RIGHT_X);
        double yVAl = joystick.getAxisValue(SmartJoystick.Axis.RIGHT_Y);
        double power = Math.sqrt(xVal*xVal + yVAl*yVAl);
        double angle = Math.atan(yVAl/xVal);
        try {
            chassis.moveMotors(new double[]{power, power, power, power}, new double[]{angle, angle, angle, angle});
        } catch (MotorPowerOutOfRangeException e) {
            e.printStackTrace();
        }
    }
}
