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

    private final WPI_TalonSRX m_Angle;
    private final CANSparkMax m_Drive;
    private final IEncoder angleEncoder;
    private final SparkEncoder driveEncoder;
    private int ID;
    private boolean isDriverInverted, isRotatorInverted;

    SwerveModule(int rotatePort, int drivePort, int ID) { // I'm not sure how to give port numbers in init' should i just add theme to init?
        this.ID = ID;
        isDriverInverted = false;
        isRotatorInverted = false;
        m_Angle = new WPI_TalonSRX(rotatePort);
        m_Drive = new CANSparkMax(drivePort, CANSparkMaxLowLevel.MotorType.kBrushless);
        angleEncoder = new TalonEncoder(RobotMap.Limbo2.Chassis.SwerveModule.NORMALIZER_SRX, m_Angle);// again, values from past code
        driveEncoder = new SparkEncoder(RobotMap.Limbo2.Chassis.SwerveModule.NORMALIZER_SPARK, m_Drive);
    }

    public int getTicks(){ return angleEncoder.getRawTicks();}
    public int getNormalizedTicks(){ return getTicks()%1024; }

    public int getDegrees(){ return getTicks() * 360/1024;}

    public int getNormalizedDegrees(){return getNormalizedTicks() * 360/1024;}

    public int getID() {
        return ID;
    }

    public WPI_TalonSRX getM_Angle() {
        return m_Angle;
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

    public double getLinVel(){
        return driveEncoder.getNormalizedVelocity();
    }

    public double getAngVel(){
        return angleEncoder.getNormalizedVelocity();
    }
    
    public boolean isDriverInverted() {
        return isDriverInverted;
    }

    public boolean isRotatorInverted() {
        return isRotatorInverted;
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

    public void setAsFollowerOf(double portID){
        m_Angle.set(ControlMode.Follower, portID);
    }

    public void setPower(double power){
        m_Drive.set(power);
    }

    public void setAngle(double destDegrees){
        double destTicks = destDegrees * 1024/360;
        m_Angle.set(ControlMode.Position, destTicks);
    }
}