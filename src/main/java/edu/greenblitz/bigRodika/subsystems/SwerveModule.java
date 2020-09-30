package edu.greenblitz.bigRodika.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.gblib.encoder.IEncoder;
import edu.greenblitz.gblib.encoder.TalonEncoder;
import edu.greenblitz.gblib.gears.GearDependentValue;

public class SwerveModule extends GBSubsystem {

    private static SwerveModule instance;
    private WPI_TalonSRX mRotate;
    private CANSparkMax mDrive;
    private IEncoder angleEncoder;

    SwerveModule(int rotatePort, int drivePort) { // I'm not sure how to give port numbers in init' should i just add theme to init?
        mRotate = new WPI_TalonSRX(rotatePort);
        mDrive = new CANSparkMax(drivePort, CANSparkMaxLowLevel.MotorType.kBrushless); // TODO: check device type (2nd arg)
        angleEncoder = new TalonEncoder(RobotMap.Limbo2.Chassis.SwerveModule.NORMALIZER,mRotate);// again, values from past code
    }

    public static SwerveModule getInstance(){ return instance; }

    public void setAngle(double dest){
        double dAngle =  dest - getNormAngleDegs();
        if(dAngle > 0 && dAngle < 180){
            mRotate.set(1);
        }
        else if(dAngle > 0 && dAngle < 180){
            mRotate.set(-1);
        }

        if(dAngle < 0 && dAngle < 180){
            mRotate.set(-1);
        }
        else if(dAngle < 0 && dAngle < 180){
            mRotate.set(1);
        }
    }

    public void setPower(double power){
        mDrive.set(power);
    }

    public double getNormAngleRads() {
        //I chose the numbers according to the values used in past code
        return 2 * Math.PI * (angleEncoder.getNormalizedTicks() - 8974.0 / new GearDependentValue<>(28672.0,
                28672.0).getValue());
    }

    public double getNormAngleDegs() {
        return Math.toDegrees(getNormAngleRads());
    }

    public WPI_TalonSRX getmRotate() {
        return mRotate;
    }

    public CANSparkMax getmDrive() {
        return mDrive;
    }

    public IEncoder getAngleEncoder() {
        return angleEncoder;
    }
}