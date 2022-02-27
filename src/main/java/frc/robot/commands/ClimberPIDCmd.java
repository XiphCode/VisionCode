package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberPIDCmd extends CommandBase {
    private ClimberSubsystem climber;
    private int direction;
    private PIDController controller;

    public ClimberPIDCmd(ClimberSubsystem climber, int direction) {
        this.climber = climber;
        addRequirements(climber);
        this.direction = direction;

        this.controller = new PIDController(0.05, 0, 0);
        double halfSetpoint = -900.0 / 2.0;
        controller.setSetpoint(direction * halfSetpoint + halfSetpoint);
        controller.setTolerance(10);
    }

    @Override
    public void initialize() {
        System.out.println("ClimberControl init " + controller.getSetpoint());
    }

    @Override
    public void execute() {
        double val = controller.calculate(climber.getLeftEncoder().getPosition());
        climber.setLeft(val);
        System.out.println("ClimberControl execute " + val + " " + controller.getSetpoint());
    }

    @Override
    public boolean isFinished() {
        return controller.atSetpoint();
    }

    @Override
    public void end(boolean isInterrupted) {
        System.out.println("ClimberControl end");
        climber.setLeft(0);
        climber.setRight(0);
    }
}
