package edu.greenblitz.bigRodika.commands.complex.autonomous;

import edu.greenblitz.bigRodika.commands.chassis.motion.DumbAlign;
import edu.greenblitz.bigRodika.commands.chassis.motion.PreShoot;
import edu.greenblitz.bigRodika.commands.dome.DomeMoveByConstant;
import edu.greenblitz.bigRodika.commands.dome.ResetDome;
import edu.greenblitz.bigRodika.commands.funnel.InsertIntoShooter;
import edu.greenblitz.bigRodika.commands.intake.extender.ExtendRoller;
import edu.greenblitz.bigRodika.commands.shooter.pidshooter.threestage.autonomous.ThreeStageForAutonomous;
import edu.greenblitz.bigRodika.subsystems.Dome;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import org.greenblitz.motion.base.State;

import java.util.ArrayList;
import java.util.List;

public class ThreeBallSimple extends ParallelCommandGroup {

    public ThreeBallSimple() {

        List<State> hardCodedShit = new ArrayList<>();
        hardCodedShit.add(new State(0, 0));
        hardCodedShit.add(new State(0, -1));

        addCommands(
                new SequentialCommandGroup(
                        new WaitCommand(5.0),
                        new ThreeStageForAutonomous(3700, 0.65)
                ),
                new SequentialCommandGroup(
                        new DomeMoveByConstant(0.3).withTimeout(0.2),
                        new ResetDome(-0.3),
                        new ExtendRoller(),
                        new WaitCommand(0.4),
                        new PreShoot(
                                new DumbAlign(4.0, .1, .3)),
//                        new ParallelRaceGroup(
//                                new TurretByVision(VisionMaster.Algorithm.HEXAGON),
//                                new ThreadedCommand(new Follow2DProfileCommand(hardCodedShit,
//                                        RobotMap.Limbo2.Chassis.MotionData.CONFIG, 0.3, true),
//                                        Chassis.getInstance())
//                        ),
//                        new DomeApproachSwiftly(RobotMap.Limbo2.Dome.DOME.get(4.0)),
                        new InsertIntoShooter(1, 0.5, 0.1)
                )

        );

        m_requirements.remove(Dome.getInstance());

    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        SmartDashboard.putBoolean("Auto interrupted", interrupted);
    }
}
