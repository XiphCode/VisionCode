package frc.robot.commands;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class IntakeCommand extends CommandBase {
    private final Consumer<Double> intake;

    public IntakeCommand(Subsystem subsystem, Consumer<Double> intake) {
        addRequirements(subsystem);
        this.intake = intake;
    } 

    @Override
    public void initialize() {
        intake.accept(1.0);
    }

    @Override
    public void end(boolean interrupted) {
        intake.accept(0.0);
    }
}
