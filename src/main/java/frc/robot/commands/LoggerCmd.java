package frc.robot.commands;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class LoggerCmd extends CommandBase{
    private RelativeEncoder leftEncoder;
    private RelativeEncoder rightEncoder;

    public LoggerCmd(ClimberSubsystem climber) {
        this.leftEncoder = climber.getLeftEncoder();
        this.rightEncoder = climber.getRightEncoder();
        // no need to addRequirements()
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
    }
}
