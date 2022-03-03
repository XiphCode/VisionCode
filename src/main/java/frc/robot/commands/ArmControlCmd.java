package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class ArmControlCmd extends CommandBase {
    private final ArmSubsystem arm;
    private final int direction;

    public ArmControlCmd(ArmSubsystem arm, int direction) {
        this.arm = arm;
        this.direction = direction;
    }

    @Override
    public void execute() {
        arm.set(direction);
    }
    
    @Override
    public void end(boolean interrupted) {
        arm.set(0);
    }
}
