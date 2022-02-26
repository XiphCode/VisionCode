package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberControlCmd extends CommandBase {
    private ClimberSubsystem climber;
    private int direction;

    public ClimberControlCmd(ClimberSubsystem climber, int direction) {
        this.climber = climber;
        this.direction = direction;
        addRequirements(climber);
        climber.getLeftEncoder();
        climber.getRightEncoder();
    }

    @Override
    public void initialize() {
        System.out.println("CLIMBER CONTROL INIT " + direction);
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
        System.out.println("CLIMBER CONTROL END " + direction);
    }
}
