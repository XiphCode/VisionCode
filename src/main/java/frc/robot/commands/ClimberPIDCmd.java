package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberPIDCmd extends CommandBase {
    private final ClimberSubsystem climber;
    private final PIDController leftController, rightController;

    public ClimberPIDCmd(ClimberSubsystem climber, int direction) {
        this.climber = climber;
        addRequirements(climber);
        this.leftController = getController(direction);
        this.rightController = getController(direction);
    }

    private PIDController getController(int direction) {
        PIDController c = new PIDController(0.005, 0, 0);
        c.setSetpoint(direction == -1 ? 0.0 : 930.0);
        c.setTolerance(10);
        return c;
    }

    @Override
    public void initialize() {
        System.out.println("ClimberControl init " + leftController.getSetpoint());
    }

    @Override
    public void execute() {
        double leftVal = 0, rightVal = 0;
        if (!leftController.atSetpoint()) {
            leftVal = leftController.calculate(climber.getLeftEncoder().getPosition());
        }
        climber.setLeft(-leftVal);
        if (!rightController.atSetpoint()) {
            rightVal = rightController.calculate(climber.getRightEncoder().getPosition());
        }
        climber.setRight(-rightVal);
        System.out.println("ClimberControl execute " + leftVal + ", " + rightVal);
    }

    @Override
    public boolean isFinished() {
        return leftController.atSetpoint() && rightController.atSetpoint();
    }

    @Override
    public void end(boolean isInterrupted) {
        System.out.println("ClimberControl end");
        climber.setLeft(0);
        climber.setRight(0);
    }
}
