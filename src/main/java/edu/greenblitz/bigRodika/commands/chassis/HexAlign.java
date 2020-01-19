package edu.greenblitz.bigRodika.commands.chassis;

import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.bigRodika.commands.chassis.profiling.Follow2DProfileCommand;
import edu.greenblitz.bigRodika.subsystems.Chassis;
import edu.greenblitz.bigRodika.utils.VisionMaster;
import edu.greenblitz.gblib.command.GBCommand;
import edu.greenblitz.gblib.threading.ThreadedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.greenblitz.motion.base.Point;
import org.greenblitz.motion.base.State;
import org.greenblitz.motion.pid.PIDObject;
import org.greenblitz.motion.profiling.ProfilingData;

import java.util.ArrayList;
import java.util.List;

public class HexAlign extends GBCommand {

    private Follow2DProfileCommand prof;
    private double k = 0.2;
    private double r = 2; //radius

    public HexAlign(double r, double k){
        this.k = k;
        this.r = r;
    }
    public HexAlign(){

    }

    @Override
    public void initialize(){
        State startState = new State(Chassis.getInstance().getLocation(), -Chassis.getInstance().getAngle());
        VisionMaster.Algorithm.HEXAGON.setAsCurrent();
        double[] difference = VisionMaster.getInstance().getVisionLocation().toDoubleArray();
        double targetX = difference[0];
        double targetY = difference[1];
        //assume targetY != 0
        double relAng = Math.atan(targetX/targetY);
        double absAng = Chassis.getInstance().getAngle();

        Point hex = new Point(targetX*Math.cos(absAng) - targetY*Math.sin(absAng) + startState.getX(),targetY*Math.cos(absAng) + targetX*Math.sin(absAng) + startState.getY());
        SmartDashboard.putString("hex", hex.toString());
        System.err.println("hex " + hex.toString());

        double angle = (Math.abs(Math.sin(-relAng)*targetY/r) > 1) ? Math.PI/2 - absAng + relAng : Math.PI/2 - absAng + relAng - k*Math.asin(Math.sin(-relAng)*targetY/r);
        State endState = new State(hex.getX() + r*Math.cos(angle), hex.getY() - r*Math.sin(angle), -(Math.PI / 2 - angle));

        List<State> path = new ArrayList<>();
        path.add(startState);
        path.add(endState);

        SmartDashboard.putString("end", endState.toString());
        System.err.println("end" + endState.toString());

        ProfilingData data = RobotMap.BigRodika.Chassis.MotionData.POWER.get("0.7");

/**
        prof = new Follow2DProfileCommand(path,
                .0002, 1000,
                data,
                0.7, 1, 1,
                new PIDObject(0*data.getMaxLinearVelocity(), 0, 0*data.getMaxLinearAccel()), .01*data.getMaxLinearVelocity(),
                new PIDObject(0*data.getMaxAngularVelocity(), 0, 0*data.getMaxAngularAccel()), .01*data.getMaxAngularVelocity(),
                false);
**/
        prof = new Follow2DProfileCommand(path,
                .001, 1000,
                data,
                0.7, 1, 1,
                new PIDObject(0.26*data.getMaxLinearVelocity(), 0, 1.8*data.getMaxLinearAccel()), .01*data.getMaxLinearVelocity(),
                new PIDObject(0.14*data.getMaxAngularVelocity(), 0, 0*data.getMaxAngularAccel()), .01*data.getMaxAngularVelocity(),
                false);
    }

    @Override
    public void execute(){
    }

    @Override
    public void end(boolean interupted) {
        new ThreadedCommand(prof, Chassis.getInstance()).schedule();

    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
