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
        pid = new PIDController(1, 0, 0);
        pid.setSetpoint(0);
    }

    @Override
    public void execute() {
        double ballX = SmartDashboard.getNumber("BallX", -99);
        if (-1 <= ballX && ballX <= 1) {
            double val = pid.calculate(ballX);
            driveSubsystem.driveCartesian(0, 0, val);
        }
    }
}
