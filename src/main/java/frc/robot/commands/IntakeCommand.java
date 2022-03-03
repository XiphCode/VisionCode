package frc.robot.commands;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class IntakeCommand extends CommandBase {
    public interface IntakeSubsytem extends Subsystem {
        public void intakeSet(double val);
    }

    private final IntakeSubsytem intake;

    public IntakeCommand(IntakeSubsytem intake) {
        addRequirements(intake);
        this.intake = intake;
    } 

    @Override
    public void initialize() {
        intake.intakeSet(1.0);
    }

    @Override
    public void end(boolean interrupted) {
        intake.intakeSet(0.0);
    }
}
