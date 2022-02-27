package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberControlCmd extends CommandBase {
    private ClimberSubsystem climber;
    private Supplier<Double> controlSupplier;
    
    public ClimberControlCmd(ClimberSubsystem climber, Supplier<Double> controlSupplier) {
        this.climber = climber;
        addRequirements(climber);
        this.controlSupplier = controlSupplier;
    }

    @Override
    public void execute() {
        climber.setLeft(controlSupplier.get());
    }

    @Override
    public void end(boolean interrupted) {
        climber.setLeft(0);
    }
}
