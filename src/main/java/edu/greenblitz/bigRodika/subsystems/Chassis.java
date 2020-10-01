package edu.greenblitz.bigRodika.subsystems;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.gblib.gyroscope.IGyroscope;


public class Chassis extends GBSubsystem {
    private static Chassis instance;

    private final SwerveModule[] swerveModules = new SwerveModule[4];

    private IGyroscope gyroscope;
//    private PowerDistributionPanel robotPDP;

    private Chassis() {
        SwerveModule frontLeft = new SwerveModule(RobotMap.Limbo2.Chassis.Motor.FRONT_LEFT.ROTATE_PORT,
                RobotMap.Limbo2.Chassis.Motor.FRONT_LEFT.DRIVE_PORT, RobotMap.Limbo2.Chassis.Motor.FRONT_LEFT.ID);
        swerveModules[frontLeft.getID()] = frontLeft;
        SwerveModule frontRight = new SwerveModule(RobotMap.Limbo2.Chassis.Motor.FRONT_RIGHT.ROTATE_PORT,
                RobotMap.Limbo2.Chassis.Motor.FRONT_RIGHT.DRIVE_PORT, RobotMap.Limbo2.Chassis.Motor.FRONT_RIGHT.ID);
        swerveModules[frontRight.getID()] = frontRight;
        SwerveModule backLeft = new SwerveModule(RobotMap.Limbo2.Chassis.Motor.BACK_LEFT.ROTATE_PORT,
                RobotMap.Limbo2.Chassis.Motor.BACK_LEFT.DRIVE_PORT, RobotMap.Limbo2.Chassis.Motor.BACK_LEFT.ID);
        swerveModules[backLeft.getID()] = backLeft;
        SwerveModule backRight = new SwerveModule(RobotMap.Limbo2.Chassis.Motor.BACK_RIGHT.ROTATE_PORT,
                RobotMap.Limbo2.Chassis.Motor.BACK_RIGHT.DRIVE_PORT, RobotMap.Limbo2.Chassis.Motor.BACK_RIGHT.ID);
        swerveModules[backRight.getID()] = backRight;
    }

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
        for (SwerveModule swerveModule : swerveModules){
            swerveModule.setPower(Math.max(Math.min(powers[swerveModule.getID()], 1), -1));
            swerveModule.setAngle(angles[swerveModule.getID()]);
        }
    }

    public void toBrake() {
        for (SwerveModule swerveModule : swerveModules) {
            swerveModule.getM_Drive().setIdleMode(CANSparkMax.IdleMode.kBrake);
        }
    }

    public void toCoast() {
        for (SwerveModule swerveModule : swerveModules) {
            swerveModule.getM_Drive().setIdleMode(CANSparkMax.IdleMode.kCoast);
        }
    }

    public void arcadeDrive(double power, double rotate) {
        double[] powers = {power - rotate, power + rotate, power - rotate, power + rotate};
        double[] angles = {0, 0, 0, 0};
        moveMotors(powers, angles);
    }

    public double[] getMeters() {
        return new double[]{swerveModules[0].getAngleEncoder().getNormalizedTicks(), swerveModules[0].getAngleEncoder().getNormalizedTicks(),
                swerveModules[0].getAngleEncoder().getNormalizedTicks(), swerveModules[0].getAngleEncoder().getNormalizedTicks()};
    }

    public double[] getRates() {
        return new double[]{swerveModules[0].getAngleEncoder().getNormalizedVelocity(), swerveModules[0].getAngleEncoder().getNormalizedVelocity(),
                swerveModules[0].getAngleEncoder().getNormalizedVelocity(), swerveModules[0].getAngleEncoder().getNormalizedVelocity()};
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
        for (SwerveModule swerveModule : swerveModules){
            swerveModule.getAngleEncoder().reset();
        }
    }

}