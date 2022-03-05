package frc.robot.commands;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimberSubsystem;

public class LoggerCmd extends CommandBase{
    private RelativeEncoder leftEncoder;
    private RelativeEncoder rightEncoder;
    private RelativeEncoder armEncoder;

    public LoggerCmd(ClimberSubsystem climber, ArmSubsystem arm) {
        this.leftEncoder = climber.getLeftEncoder();
        this.rightEncoder = climber.getRightEncoder();
        this.armEncoder = arm.getEncoder();
        // no need to addRequirements()
    } 

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

    @Override
    public void initialize() {
        System.out.println("LoggerCmd initialize");
        resetEncoders();
    }

    @Override
    public void execute() {
        SmartDashboard.putNumber("ClimberL", leftEncoder.getPosition());
        SmartDashboard.putNumber("ClimberR", rightEncoder.getPosition());
        SmartDashboard.putNumber("Arm", armEncoder.getPosition());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public void resetEncoders() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
        armEncoder.setPosition(0);
    }
}
