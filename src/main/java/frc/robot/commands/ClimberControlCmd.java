package frc.robot.commands;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimberControlCmd extends CommandBase {
    private Climber climber;
    private RelativeEncoder leftEncoder;
    private RelativeEncoder rightEncoder;
    private int direction;

    public ClimberControlCmd(Climber climber, int direction) {
        this.climber = climber;
        this.leftEncoder = climber.getLeftEncoder();
        this.rightEncoder = climber.getRighEncoder();
        this.direction = direction;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    @Override
    public void execute() {
        SmartDashboard.putNumber("ClimberL", leftEncoder.getPosition());
        SmartDashboard.putNumber("ClimberR", rightEncoder.getPosition());
        if (!doneRotating(leftEncoder)) {
            climber.setLeft(direction);
        }
        if (!doneRotating(rightEncoder)) {
            climber.setRight(direction);
        }
    }

    private boolean doneRotating(RelativeEncoder encoder) {
        return Math.abs(encoder.getPosition()) > 400;
    }

    @Override
    public boolean isFinished() {
        return doneRotating(leftEncoder) && doneRotating(rightEncoder);
    }

    @Override
    public void end(boolean isFinished) {
        climber.setLeft(0);
        climber.setRight(0);
    }
}
