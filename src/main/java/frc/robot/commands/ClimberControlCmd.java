package frc.robot.commands;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimberControlCmd extends CommandBase {
    private Climber climber;
    private int direction;

    public ClimberControlCmd(Climber climber, int direction) {
        this.climber = climber;
        this.direction = direction;
        addRequirements(climber);
    }

    @Override
    public void execute() {
        climber.setLeft(direction);
        climber.setRight(direction);
    }

    @Override
    public void end(boolean isInterrupted) {
        climber.setLeft(0);
        climber.setRight(0);
    }
}
