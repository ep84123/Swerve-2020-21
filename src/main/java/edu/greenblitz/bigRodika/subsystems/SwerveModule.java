package edu.greenblitz.bigRodika.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.gblib.encoder.IEncoder;
import edu.greenblitz.gblib.encoder.SparkEncoder;
import edu.greenblitz.gblib.encoder.TalonEncoder;

public class SwerveModule extends GBSubsystem {

    private final WPI_TalonSRX m_Rotation;
    private final CANSparkMax m_Drive;
    private final SparkEncoder driveEncoder;
    private final int ID;
    private boolean isDriverInverted, isRotatorInverted;

    SwerveModule(int rotatePort, int drivePort, int ID) { // I'm not sure how to give port numbers in init' should i just add theme to init?
        this.ID = ID;
        this.isDriverInverted = false;
        this.isRotatorInverted = false;
        m_Rotation = new WPI_TalonSRX(rotatePort);
        m_Drive = new CANSparkMax(drivePort, CANSparkMaxLowLevel.MotorType.kBrushless); // TODO: check device type (2nd arg)
        driveEncoder = new SparkEncoder(RobotMap.Limbo2.Chassis.SwerveModule.NORMALIZER_SPARK, m_Drive);
    }

    public void setAngle(double destDegrees){
        double destTicks = destDegrees * 1024/360;
        m_Rotation.set(ControlMode.Position, destTicks);
    }

    public void setDrivePower(double power){
        m_Drive.set(power);
    }

    public void setRotationPower(double power){
        m_Rotation.set(power);
    }

    public void setAsFollowerOf(double portID){
        m_Rotation.set(ControlMode.Follower, portID);
    }

    public void totalInvert(){
        isDriverInverted = true;
        isRotatorInverted = true;
    }

    public void driverInvert(){
        isDriverInverted = true;
    }

    public void rotatorInvert(){
        isRotatorInverted = true;
    }

    public boolean isDriverInverted() {
        return isDriverInverted;
    }

    public boolean isRotatorInverted() {
        return isRotatorInverted;
    }

    public int getTicks(){ return getAngleEncoder().getAnalogIn(); }

    public int getNormalizedTicks(){ return getTicks()%1024; }

    public int getDegrees(){ return getTicks() * 360/1024; }

    public int getNormalizedDegrees(){return getNormalizedTicks() * 360/1024; }

    public double getLinVel(){
        return driveEncoder.getNormalizedVelocity();
    }

    public double getAngVel(){
        return getAngleEncoder().getAnalogInVel();
    }

    public double getNormalizedAngVel(){
        return getAngVel() * 20 * Math.PI/1024;
    }

    public WPI_TalonSRX getM_Rotation() {
        return m_Rotation;
    }

    public CANSparkMax getM_Drive() {
        return m_Drive;
    }

    public SensorCollection getAngleEncoder(){
        return getM_Rotation().getSensorCollection();
    }

    public SparkEncoder getDriveEncoder() {
        return driveEncoder;
    }

    public int getID() {
        return ID;
    }
}
