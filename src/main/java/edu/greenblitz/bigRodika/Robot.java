package edu.greenblitz.bigRodika;

import edu.greenblitz.bigRodika.commands.chassis.ArcadeDrive;
import edu.greenblitz.bigRodika.commands.chassis.LocalizerCommandRunner;
import edu.greenblitz.bigRodika.subsystems.Chassis;
import edu.greenblitz.bigRodika.utils.VisionLocation;
import edu.greenblitz.bigRodika.utils.VisionMaster;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.util.Arrays;

public class Robot extends TimedRobot {

    @Override
    public void robotInit() {
        OI.getInstance();
        Chassis.getInstance();
        CommandScheduler.getInstance().enable();
        VisionMaster.getInstance();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        VisionMaster.getInstance().update();
    }

    @Override
    public void teleopInit() {
    }
}
