package edu.greenblitz.bigRodika.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.gblib.encoder.SparkEncoder;
import edu.wpi.first.wpilibj.CAN;
import org.greenblitz.motion.pid.PIDObject;

public class SwerveModule extends GBSubsystem {

    private final WPI_TalonSRX m_Rotation;
    private final CANSparkMax m_Drive;
    private final SparkEncoder driveEncoder;
    private final int ID;
    private boolean isDriverInverted, isRotatorInverted;
    private PIDObject rotatePID;
    private static int rotatePIDSlot = 0, rotatePIDIdx = 0;
    private static int timeoutMs = 20;

    /**
     * SwerveModule Ctor without PID (default PID is 0 0 0 0)
     * @param rotatePort - port of the rotate motor
     * @param drivePort - port of the drive motor
     * @param ID - ID of the module (used in chassis)
     */
    SwerveModule(int rotatePort, int drivePort, int ID) { // I'm not sure how to give port numbers in    init' should i just add theme to init?
        this.ID = ID;
        this.isDriverInverted = false;
        this.isRotatorInverted = false;
        m_Rotation = new WPI_TalonSRX(rotatePort);
        m_Drive = new CANSparkMax(drivePort, CANSparkMaxLowLevel.MotorType.kBrushless); // TODO: check device type (2nd arg)
        driveEncoder = new SparkEncoder(RobotMap.Limbo2.Chassis.SwerveModule.NORMALIZER_SPARK, m_Drive);
        setRotatePID(new PIDObject(0.0,0.0,0.0,0.0));
        m_Rotation.configSelectedFeedbackSensor(FeedbackDevice.Analog,rotatePIDIdx,timeoutMs);
        //m_Rotation.setSensorPhase(true); sets the encoder direction in compare to the motor direction
    }

    /**
     * SwerveModule Ctor without PID (default PID is 0 0 0 0)
     * @param rotatePort - port of the rotate motor
     * @param drivePort - port of the drive motor
     * @param ID - ID of the module (used in chassis)
     * @param rotatePID - PID for rotation
     */
    SwerveModule(int rotatePort, int drivePort, int ID, PIDObject rotatePID) { // I'm not sure how to give port numbers in    init' should i just add theme to init?
        this(rotatePort, drivePort, ID);
        setRotatePID(rotatePID);
    }

    /**
     *
     * @param destRads - the destiny angle in radians
     */
    public void setAngle(double destRads){
        double destTicks = destRads * 1024/(2 * Math.PI);
        m_Rotation.set(ControlMode.Position, destTicks);
    }

    /**
     *
     * @param power - power to give to the motor between -1 and 1
     */
    public void setDrivePower(double power){
        m_Drive.set(power);
    }

    /**
     *
     * @param power - power to give to the motor between -1 and 1
     */
    public void setRotationPower(double power){
        m_Rotation.set(power);
    }

    public void followRotation(double leaderPort){
        m_Rotation.set(ControlMode.Follower, leaderPort);
    }

    public void followDrive(CANSparkMax leader){
        m_Drive.follow(leader);
    }

    public void follow(double rotationLeader, CANSparkMax driveLeader){
        followRotation(rotationLeader);
        followDrive(driveLeader);
    }

    public void totalInvert(boolean driveInvert, boolean rotateInvert){
        this.driverInvert(driveInvert);
        this.rotatorInvert(rotateInvert);
    }

    public void driverInvert(boolean invert){
        m_Drive.setInverted(invert);
        isDriverInverted = invert;
    }

    public void rotatorInvert(boolean invert){
        m_Rotation.setInverted(invert);
        isRotatorInverted = invert;
    }

    public void setRotatePID(PIDObject pid){
        this.rotatePID = pid;
        m_Rotation.config_kP(rotatePIDSlot,pid.getKp(),timeoutMs);
        m_Rotation.config_kI(rotatePIDSlot,pid.getKi(),timeoutMs);
        m_Rotation.config_kD(rotatePIDSlot,pid.getKd(),timeoutMs);
        m_Rotation.config_kF(rotatePIDSlot,pid.getKf(),timeoutMs);
    }

    public boolean isDriverInverted() {
        return isDriverInverted;
    }

    public boolean isRotatorInverted() {
        return isRotatorInverted;
    }

    public int getRotationTicks(){ return getRotationEncoder().getAnalogIn(); }

    public int getRotationNormalizedTicks(){ return getRotationTicks()%1024; }

    public double getRotationRads(){ return getRotationTicks() * (2*Math.PI)/1024; }

    public double getNormalizedRotationRads(){return getRotationNormalizedTicks() * (2*Math.PI)/1024; }

    public double getDriveVel(){
        return getDriveEncoder().getNormalizedVelocity();
    }

    /**
     * Itgil wrote this complains to him
     * @return velocity in units per 0.1 sec
     */
    public double getRotationVel(){
        return getRotationEncoder().getAnalogInVel();
    }

    /**
     * Itgil wrote this complains to him
     * @return velocity in rads per sec
     */
    public double getNormalizedRotationVel(){
        return getRotationVel() * 20 * Math.PI/1024;
    }

    public WPI_TalonSRX getM_Rotation() {
        return m_Rotation;
    }

    public CANSparkMax getM_Drive() {
        return m_Drive;
    }

    public SensorCollection getRotationEncoder(){
        return getM_Rotation().getSensorCollection();
    }

    public SparkEncoder getDriveEncoder() {
        return driveEncoder;
    }

    public int getID() {
        return ID;
    }

    public PIDObject getRotatePID(){
        return this.rotatePID;
    }
}
