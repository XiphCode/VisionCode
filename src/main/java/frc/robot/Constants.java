// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public class DriveConstants {
        public static final int TALON_FL_ID = 5;
        public static final int TALON_FR_ID = 2;
        public static final int TALON_BL_ID = 20;
        public static final int TALON_BR_ID = 30;
        public static final double DRIVE_SPEED = 0.8;
    }
    public class TurnConstants {
        public static final double kP = 0.02;
        public static final double kI = 0;
        public static final double kD = 0.002;
        public static final double ANG_TOL = 10;
        public static final double VEL_TOL = 10;
    }
    public class ControllerConstants {
        public static final int CONTROLLER_PORT = 0;
    }
    public class ArmConstants {
        public static final int ARM_SPARK = 3;
        public static final int ARM_COUNTS_PER_REV = 8192;
    }
    public class IntakeConstants {
        public static final int INNER_TALON_ID = 28;
        public static final int OUTER_TALON_ID = 22;
        public static final double INNER_POWER = -0.8; 
        public static final double OUTER_POWER = 1.0;
    }
}
