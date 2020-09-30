package edu.greenblitz.bigRodika.subsystems;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.gblib.gyroscope.IGyroscope;


public class Chassis extends GBSubsystem {
    private static Chassis instance;

    private SwerveModule FrontLeft = new SwerveModule(RobotMap.Limbo2.Chassis.Motor.FRONT_LEFT.ROTATE_PORT, RobotMap.Limbo2.Chassis.Motor.FRONT_LEFT.DRIVE_PORT);
    private SwerveModule FrontRight = new SwerveModule(RobotMap.Limbo2.Chassis.Motor.FRONT_RIGHT.ROTATE_PORT, RobotMap.Limbo2.Chassis.Motor.FRONT_RIGHT.DRIVE_PORT);
    private SwerveModule BackLeft = new SwerveModule(RobotMap.Limbo2.Chassis.Motor.BACK_LEFT.ROTATE_PORT, RobotMap.Limbo2.Chassis.Motor.BACK_LEFT.DRIVE_PORT);
    private SwerveModule BackRight = new SwerveModule(RobotMap.Limbo2.Chassis.Motor.BACK_RIGHT.ROTATE_PORT, RobotMap.Limbo2.Chassis.Motor.BACK_RIGHT.DRIVE_PORT);

    private IGyroscope gyroscope;
//    private PowerDistributionPanel robotPDP;

    private Chassis() {}

    public static void init() {
        if (instance == null) {
            instance = new Chassis();
//            instance.setDefaultCommand();
        }
    }

    public static Chassis getInstance() {
        return instance;
    }

    public void moveMotors(double[] powers, double[] angles) {
        FrontLeft.setPower(powers[RobotMap.Limbo2.Chassis.Motor.FRONT_LEFT.ID]);
        FrontLeft.setAngle(angles[RobotMap.Limbo2.Chassis.Motor.FRONT_LEFT.ID]);
        FrontRight.setPower(powers[RobotMap.Limbo2.Chassis.Motor.FRONT_RIGHT.ID]);
        FrontRight.setAngle(angles[RobotMap.Limbo2.Chassis.Motor.FRONT_RIGHT.ID]);
        BackLeft.setPower(powers[RobotMap.Limbo2.Chassis.Motor.BACK_LEFT.ID]);
        BackLeft.setAngle(angles[RobotMap.Limbo2.Chassis.Motor.BACK_LEFT.ID]);
        BackRight.setPower(powers[RobotMap.Limbo2.Chassis.Motor.BACK_RIGHT.ID]);
        BackRight.setAngle(angles[RobotMap.Limbo2.Chassis.Motor.BACK_RIGHT.ID]);
    }

    public void toBrake() {
        FrontLeft.getmDrive().setIdleMode(CANSparkMax.IdleMode.kBrake);
        FrontRight.getmDrive().setIdleMode(CANSparkMax.IdleMode.kBrake);
        BackLeft.getmDrive().setIdleMode(CANSparkMax.IdleMode.kBrake);
        BackRight.getmDrive().setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void toCoast() {
        FrontLeft.getmDrive().setIdleMode(CANSparkMax.IdleMode.kCoast);
        FrontRight.getmDrive().setIdleMode(CANSparkMax.IdleMode.kCoast);
        BackLeft.getmDrive().setIdleMode(CANSparkMax.IdleMode.kCoast);
        BackRight.getmDrive().setIdleMode(CANSparkMax.IdleMode.kCoast);
    }

    public void arcadeDrive(double power, double rotation) {
        double[] powers = {power, power, power, power};
        double[] angles = {0, 0, 0, 0};
        moveMotors(powers, angles);
    }

    public double[] getMeters() {
        return new double[]{FrontLeft.getAngleEncoder().getNormalizedTicks(), FrontRight.getAngleEncoder().getNormalizedTicks(),
                BackLeft.getAngleEncoder().getNormalizedTicks(), BackRight.getAngleEncoder().getNormalizedTicks()};
    }

    public double[] getRates() {
        return new double[]{FrontLeft.getAngleEncoder().getNormalizedVelocity(), FrontRight.getAngleEncoder().getNormalizedVelocity(),
                BackLeft.getAngleEncoder().getNormalizedVelocity(), BackRight.getAngleEncoder().getNormalizedVelocity()};
    }

//    public double getLinearVelocity() {}
//
//    public double getAngularVelocityByWheels() {}

    public double getAngle() {
        return gyroscope.getNormalizedYaw();
    }

    public double getRawAngle() {
        return gyroscope.getRawYaw();
    }

    public double getAngularVelocityByGyro() {
        return gyroscope.getYawRate();
    }

    public void resetGyro() {
        gyroscope.reset();
    }

//    public double getWheelDistance() {}
//
//    public Position getLocation() {}

    @Override
    public void periodic() {}

    public void resetEncoders() {
        FrontLeft.getAngleEncoder().reset();
        FrontRight.getAngleEncoder().reset();
        BackLeft.getAngleEncoder().reset();
        BackRight.getAngleEncoder().reset();
    }

}