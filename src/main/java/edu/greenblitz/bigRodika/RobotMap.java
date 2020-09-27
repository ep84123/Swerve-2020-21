package edu.greenblitz.bigRodika;

import edu.greenblitz.gblib.gears.Gear;
import edu.greenblitz.gblib.gears.GearDependentValue;
import org.greenblitz.motion.interpolation.Dataset;
import org.greenblitz.motion.profiling.ProfilingConfiguration;
import org.greenblitz.motion.profiling.ProfilingData;

import java.util.HashMap;

public class RobotMap {

    public static class Limbo2 {


        public static class Joystick {
            public static final int MAIN = 0;
            public static final double MAIN_DEADZONE = 0.05;
            public static final int SIDE = 1;
            public static final double SIDE_DEADZONE = 0.05;
        }


        public static class Chassis {
            public static final double WHEEL_DIST = 0.622;
        }
    }
}
