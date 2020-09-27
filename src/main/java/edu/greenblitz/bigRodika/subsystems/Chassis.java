package edu.greenblitz.bigRodika.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.bigRodika.OI;
import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.bigRodika.commands.chassis.driver.ArcadeDrive;
import edu.greenblitz.gblib.encoder.IEncoder;
import edu.greenblitz.gblib.encoder.SparkEncoder;
import edu.greenblitz.gblib.gyroscope.IGyroscope;
import edu.greenblitz.gblib.gyroscope.PigeonGyro;
import org.greenblitz.motion.Localizer;
import org.greenblitz.motion.base.Position;


public class Chassis extends GBSubsystem {
    private static Chassis instance;

    private CANSparkMax rightLeader, rightFollower1, rightFollower2, leftLeader, leftFollower1, leftFollower2;
    private IEncoder leftEncoder, rightEncoder;
    private IGyroscope gyroscope;
//    private PowerDistributionPanel robotPDP;

    private Chassis() {}

    public static void init() {
        if (instance == null) {
            instance = new Chassis();
            instance.setDefaultCommand(
                    new ArcadeDrive(OI.getInstance().getMainJoystick())
            );
        }
    }

    public static Chassis getInstance() {
        return instance;
    }

    public void moveMotors() {}

    public void toBrake() {}

    public void toCoast() {}

    public void arcadeDrive() {}

    public double getMeters() {}

    public double getRate() {}

    public double getLinearVelocity() {}

    public double getAngularVelocityByWheels() {}

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

    public double getWheelDistance() {}

    public Position getLocation() {}

    @Override
    public void periodic() {}

    public void resetEncoders() {}

}
