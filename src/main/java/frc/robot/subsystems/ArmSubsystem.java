package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder.Type;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private CANSparkMax spark = new CANSparkMax(3, MotorType.kBrushed);
    private RelativeEncoder encoder = spark.getEncoder(Type.kQuadrature, 8192);

    public ArmSubsystem() {} 

    public RelativeEncoder getEncoder() {
        return encoder;
    }
}
