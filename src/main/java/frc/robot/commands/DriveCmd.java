package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCmd extends CommandBase {
    private DriveSubsystem driveSubsystem;
    private double driveVal;

    public DriveCmd(DriveSubsystem driveSubsystem, double driveVal) {
        this.driveSubsystem = driveSubsystem;
        this.driveVal = driveVal;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("DriveCmd start");
    }

    @Override
    public void execute() {
        driveSubsystem.driveCartesian(driveVal, 0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("DriveCmd end");
    }
}
