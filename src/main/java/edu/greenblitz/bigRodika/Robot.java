package edu.greenblitz.bigRodika;

import edu.greenblitz.bigRodika.commands.chassis.locazlier.LocalizerCommandRunner;
import edu.greenblitz.bigRodika.commands.climber.ClimbByTriggers;
import edu.greenblitz.bigRodika.commands.climber.HookByTriggers;
import edu.greenblitz.bigRodika.commands.complex.autonomous.FiveBallTrench;
import edu.greenblitz.bigRodika.commands.dome.ResetDome;
import edu.greenblitz.bigRodika.commands.shooter.StopShooter;
import edu.greenblitz.bigRodika.commands.turret.resets.ResetEncoderWhenInBack;
import edu.greenblitz.bigRodika.commands.turret.resets.ResetEncoderWhenInSide;
import edu.greenblitz.bigRodika.subsystems.*;
import edu.greenblitz.bigRodika.utils.DigitalInputMap;
import edu.greenblitz.bigRodika.utils.UARTCommunication;
import edu.greenblitz.bigRodika.utils.VisionMaster;
import edu.greenblitz.gblib.gears.Gear;
import edu.greenblitz.gblib.gears.GlobalGearContainer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.greenblitz.motion.Localizer;

public class Robot extends TimedRobot {

    @Override
    public void robotInit() {

        Chassis.init();
        OI.getInstance();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void teleopPeriodic() {

    }

    @Override
    public void autonomousInit() {

    }

    @Override
    public void teleopInit() {
        CommandScheduler.getInstance().cancelAll();

    }
}
