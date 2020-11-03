package edu.greenblitz.bigRodika.tests;

import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.bigRodika.RobotMap.Limbo2.Chassis.Motor.FRONT_RIGHT;
import edu.greenblitz.bigRodika.exceptions.MotorPowerOutOfRangeException;
import edu.greenblitz.bigRodika.subsystems.OneModuleChassis;
import edu.greenblitz.bigRodika.subsystems.SwerveModule;
import edu.greenblitz.gblib.command.GBCommand;
import edu.greenblitz.gblib.hid.SmartJoystick;

public class OneModuleTest extends GBCommand {
    private OneModuleChassis module;
    private final SmartJoystick joystick;
    private final double POWER_SCALE = 0.5;

    public OneModuleTest(SmartJoystick joystick){
        this.joystick = joystick;
        module = new OneModuleChassis(FRONT_RIGHT.ROTATE_PORT, FRONT_RIGHT.DRIVE_PORT, FRONT_RIGHT.ID);
    }

    @Override
    public void initialize() {
        module.setRotationPower(0);
        module.setDrivePower(0);
    }

    @Override
    public void execute() {
        double xVal = joystick.getAxisValue(SmartJoystick.Axis.LEFT_X);
        double yVal = joystick.getAxisValue(SmartJoystick.Axis.LEFT_Y);
        module.setDrivePower(yVal*0.5);
        module.setRotationPower(xVal*0.5);
        module.putString("One Module Chassis", module.toString());
    }


}
