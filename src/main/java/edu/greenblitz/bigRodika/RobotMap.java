package edu.greenblitz.bigRodika;

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

            public static class Motor {
                public static class FRONT_RIGHT {
                    public static final int MODULE_PORT = , DRIVE_PORT = ;
                }
                public static class FRONT_LEFT {
                    public static final int MODULE_PORT = , DRIVE_PORT = ;
                }
                public static class BACK_RIGHT {
                    public static final int MODULE_PORT = , DRIVE_PORT = ;
                }
                public static class BACK_LEFT {
                    public static final int MODULE_PORT = , DRIVE_PORT = ;
                }
            }
        }
    }
}
