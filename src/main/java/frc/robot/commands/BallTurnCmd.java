package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class BallTurnCmd extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final PIDController drivePID, turnPID;

    public BallTurnCmd(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        drivePID = new PIDController(0.07, 0, 0.02);
        drivePID.setSetpoint(35);
        turnPID = new PIDController(0.7, 0, 0.07);
        turnPID.setSetpoint(0);
    }

    @Override
    public void initialize() {
        System.out.println("BallTurnCmd started");
    }

    @Override
    public void execute() {
        double ballR = SmartDashboard.getNumber("BallR", -99);
        if (10 <= ballR && ballR <= 40) {
            driveSubsystem.setDriveAuto(drivePID.calculate(ballR));
        }
        double ballX = SmartDashboard.getNumber("BallX", -99);
        if (-1 <= ballX && ballX <= 1) {
            driveSubsystem.setTurnAuto(-turnPID.calculate(ballX));
        }
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.setDriveAuto(0);
        driveSubsystem.setTurnAuto(0);
        if (interrupted) {
            System.out.println("BallTurnCmd interrupted");
        } else {
            System.out.println("BallTurnCmd finished");
        }
    }
}
