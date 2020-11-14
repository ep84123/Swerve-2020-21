package edu.greenblitz.bigRodika.commands;

import edu.greenblitz.bigRodika.Basics.Pair;
import edu.greenblitz.bigRodika.exceptions.MotorPowerOutOfRangeException;
import edu.greenblitz.bigRodika.subsystems.Chassis;
import edu.greenblitz.bigRodika.utils.DirectionHandler;
import edu.greenblitz.bigRodika.utils.DumbDH;
import edu.greenblitz.gblib.hid.SmartJoystick;

/**
 * @author Orel
 */

public class HolonomicDrive extends ChassisCommand {

    private final SmartJoystick joystick;
    private final Chassis chassis;
    private final double POWER_CONST = 1.0;
    private boolean fieldOriented = true;
    private DirectionHandler<DumbDH> dh = new DirectionHandler<>(new DumbDH());

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
        double xVal = joystick.getAxisValue(SmartJoystick.Axis.LEFT_X);
        double yVal = joystick.getAxisValue(SmartJoystick.Axis.LEFT_Y);
        double power = getLinearPower(xVal, yVal);
        double angle = getDriveAngle(xVal, yVal);
        //Assuming the encoders give the velocity in m/s and the angle in radians (yuck radians)
        Pair<Boolean, Double> decision = dh.handle(angle, Chassis.getInstance().getDriveVelocities()[0]);
        power = decision.getFirst() && power > 0 ? power : -power;
        angle = decision.getSecond();
        try {
            chassis.moveMotors(new double[]{power, power, power, power}, new double[]{angle, angle, angle, angle}, fieldOriented);
        } catch (MotorPowerOutOfRangeException e) {
            e.printStackTrace();
        }
    }

    public double getLinearPower(double xVal, double yVal){
        return Math.sqrt(Math.pow(xVal, 2) + Math.pow(2, yVal));
    }

    public double getDriveAngle(double xVal, double yVal){
        return Math.toDegrees(Math.atan(yVal/xVal));
    }

    private double DHBool2Int(boolean b, double power){
        if (b ^ power > 0) return 1.0;
        return -1.0;
        //Decision Table
    }


}
