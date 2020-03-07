package edu.greenblitz.bigRodika.commands.complex.multisystem;

import edu.greenblitz.bigRodika.RobotMap;
import edu.greenblitz.bigRodika.commands.turret.StopTurret;
import edu.greenblitz.bigRodika.commands.turret.help.JustGoToTheFuckingTarget;
import edu.greenblitz.bigRodika.commands.turret.help.TurretByTriggers;
import edu.greenblitz.bigRodika.subsystems.Chassis;
import edu.greenblitz.bigRodika.subsystems.Turret;
import edu.greenblitz.bigRodika.utils.VisionMaster;
import edu.greenblitz.gblib.command.GBCommand;
import edu.greenblitz.gblib.hid.SmartJoystick;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import java.util.function.Supplier;

public class CompleteShoot extends SequentialCommandGroup {

    public CompleteShoot(SmartJoystick secondStick) {

        Supplier<Double> turretAlignSupp = () ->
                Turret.getInstance().getNormAngleRads() +
                        VisionMaster.getInstance().getVisionLocation().getRelativeAngleRad();

        Supplier<Double> turretFindVisionSupp = () ->
                -Chassis.getInstance().getAngle();

        addCommands(

//                new MoveTurretByConstant(0.3).withInterrupt(() ->
//                        VisionMaster.getInstance().isLastDataValid() &&
//                                Math.abs(VisionMaster.getInstance().getVisionLocation().getRelativeAngle()) < 15)
//                        .withTimeout(2.5),
                new JustGoToTheFuckingTarget(turretFindVisionSupp,
                        Math.toRadians(2.5),
                        Math.toRadians(15.0), Math.toRadians(5.0),
                        0.6, 0.04,
                        0.02 / 0.5),
                new StopTurret(),
                new WaitCommand(0.05),
//                new ThreadedCommand(new TurretByVisionThreaded(VisionMaster.Algorithm.HEXAGON),
//                        Turret.getInstance()).withTimeout(2),
                new ParallelRaceGroup(
                        new TurretByTriggers(secondStick),
                        new GBCommand() {
                            @Override
                            public boolean isFinished() {
                                if (!VisionMaster.getInstance().isLastDataValid()) return false;
                                double planaryDist = VisionMaster.getInstance().getVisionLocation().getPlaneDistance();

                                return planaryDist < RobotMap.Limbo2.Shooter.MAXIMUM_SHOOT_DIST
                                        && planaryDist > RobotMap.Limbo2.Shooter.MINIMUM_SHOOT_DIST;
                            }
                        }),
                new ParallelCommandGroup(
                        new JustGoToTheFuckingTarget(turretAlignSupp,
                                Math.toRadians(1.0),
                                Math.toRadians(15.0), Math.toRadians(5.0),
                                0.6, 0.04,
                                0.02 / 0.5),
                        new PrepareShooterByDistance(() ->
                                VisionMaster.getInstance().getVisionLocation().getPlaneDistance()
                        )
                )

        );
    }

}
