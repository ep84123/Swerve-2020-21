package edu.greenblitz.bigRodika.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.gblib.encoder.IEncoder;
import edu.greenblitz.gblib.encoder.SparkEncoder;
import edu.greenblitz.gblib.encoder.TalonEncoder;
import edu.greenblitz.gblib.gears.GearDependentValue;

public class SwerveModule extends GBSubsystem {

    private final WPI_TalonSRX m_Rotation;
    private final CANSparkMax m_Drive;
    private final IEncoder angleEncoder;
    private final SparkEncoder driveEncoder;
    private final int ID;

    SwerveModule(int rotatePort, int drivePort, int ID) { // I'm not sure how to give port numbers in init' should i just add theme to init?
        this.ID = ID;
        m_Rotation = new WPI_TalonSRX(rotatePort);
        m_Drive = new CANSparkMax(drivePort, CANSparkMaxLowLevel.MotorType.kBrushless); // TODO: check device type (2nd arg)
        angleEncoder = new TalonEncoder(RobotMap.Limbo2.Chassis.SwerveModule.NORMALIZER_SRX, m_Rotation);// again, values from past code
        driveEncoder = new SparkEncoder(RobotMap.Limbo2.Chassis.SwerveModule.NORMALIZER_SPARK, m_Drive);
    }

    public void setAngle(double destAngleDegs){
        double destAngleTicks = degs2NormalizedTicks(destAngleDegs);
        m_Rotation.set(ControlMode.Position, destAngleTicks);
    }

    public void setAsFollowerOf(double portID){
        m_Rotation.set(ControlMode.Follower, portID);
    }

    public void setPower(double power){
        m_Drive.set(power);
    }

    public double getNormAngleRads() {
        // I chose the numbers according to the values used in past code
        return 2 * Math.PI * (angleEncoder.getNormalizedTicks() - 8974.0 / new GearDependentValue<>(28672.0,
                28672.0).getValue());
    }

    public double getNormAngleDegs() {
        return Math.toDegrees(getNormAngleRads());
    }

    public double degs2NormalizedTicks(double alpha){
        return 2867.0*((Math.PI*alpha)/180.0) + 8974.0;
    }

    public WPI_TalonSRX getM_Rotation() {
        return m_Rotation;
    }

    public CANSparkMax getM_Drive() {
        return m_Drive;
    }

    public IEncoder getAngleEncoder() {
        return angleEncoder;
    }

    public SparkEncoder getDriveEncoder() {
        return driveEncoder;
    }

    public int getID() {
        return ID;
    }
}