package frc.robot.commands;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimberControlCmd extends CommandBase {
    private Climber climber;
    private RelativeEncoder encoder;
    private int direction;

    public ClimberControlCmd(Climber climber, int direction) {
        this.climber = climber;
        this.encoder = climber.getEncoder();
        this.direction = direction;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        encoder.setPosition(0);
    }

    @Override
    public void execute() {
        SmartDashboard.putNumber("Climber", encoder.getPosition());
        climber.set(direction);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(encoder.getPosition()) > 400;
    }

    @Override
    public void end(boolean isFinished) {
        climber.set(0);
    }
}
