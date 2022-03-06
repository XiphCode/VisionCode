package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase {
    private CANSparkMax spark = new CANSparkMax(ArmConstants.ARM_SPARK, MotorType.kBrushed);
    private RelativeEncoder encoder = spark.getEncoder(
        SparkMaxRelativeEncoder.Type.kQuadrature, ArmConstants.ARM_COUNTS_PER_REV
    );

    public ArmSubsystem() {} 

    public RelativeEncoder getEncoder() {
        return encoder;
    }

    public void set(double val) {
        spark.set(val * 0.5);
    }
}
