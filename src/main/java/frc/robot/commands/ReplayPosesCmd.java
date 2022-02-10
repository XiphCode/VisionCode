package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.DriveSubsystem.Pose;

public class ReplayPosesCmd extends CommandBase {
    private DriveSubsystem driveSubsystem;
    private int i = 0;

    public ReplayPosesCmd(DriveSubsystem d) {
        addRequirements(d);
        driveSubsystem = d;
    }

    @Override
    public void execute() {
        Pose p = driveSubsystem.poses.get(i);
        System.out.println(
            "Execute pose " + i + "/" + driveSubsystem.poses.size() +
            ": " + p.drive + " " + p.strafe + " " + p.turn
        );
        driveSubsystem.driveCartesian(p.drive, p.strafe, p.turn, false);
        i++;
    }

    @Override
    public boolean isFinished() {
        // Don't run if logging is enabled!
        return driveSubsystem.loggingEnabled || i >= driveSubsystem.poses.size();
    }
}
