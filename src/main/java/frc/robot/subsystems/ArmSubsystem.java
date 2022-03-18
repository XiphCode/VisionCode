package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase {
    private WPI_TalonSRX leftTalon = new WPI_TalonSRX(ArmConstants.ARM_LEFT_TALON);
    private CANSparkMax rightSpark = new CANSparkMax(ArmConstants.ARM_RIGHT_SPARK, MotorType.kBrushed);
    private RelativeEncoder encoder = rightSpark.getEncoder(
        SparkMaxRelativeEncoder.Type.kQuadrature, ArmConstants.ARM_COUNTS_PER_REV
    );

    public ArmSubsystem() {} 

    public RelativeEncoder getEncoder() {
        return encoder;
    }

    public void set(double val) {
        leftTalon.set(val * ArmConstants.ARM_POWER);
        rightSpark.set(val * ArmConstants.ARM_POWER);
    }
}
