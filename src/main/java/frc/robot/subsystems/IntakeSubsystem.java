package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final WPI_TalonSRX outerTalon = new WPI_TalonSRX(3);
    private final WPI_TalonSRX innerTalon = new WPI_TalonSRX(28);

    public IntakeSubsystem() {}

    public void setOuter(double val) {
        outerTalon.set(val);
    }

    public void setInner(double val) {
        innerTalon.set(val);
    }
}
