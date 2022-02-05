package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class BallTurnCmd extends CommandBase {
    private DriveSubsystem driveSubsystem;
    private PIDController pid;

    public BallTurnCmd(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        pid = new PIDController(0.7, 0, 0.07);
        pid.setSetpoint(0);
    }

    @Override
    public void initialize() {
        System.out.println("BallTurnCmd started");
    }

    @Override
    public void execute() {
        double ballX = SmartDashboard.getNumber("BallX", -99);
        if (-1 <= ballX && ballX <= 1) {
            double val = pid.calculate(ballX);
            driveSubsystem.setTurnAuto(-val);
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            System.out.println("BallTurnCmd interrupted");
        } else {
            System.out.println("BallTurnCmd finished");
        }
    }
}
