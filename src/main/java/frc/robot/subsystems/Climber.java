package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder.Type;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
    private CANSparkMax spark = new CANSparkMax(2, MotorType.kBrushed);

    public Climber() {}

    public void set(double val) {
        spark.set(val * 0.25);
    }

    public RelativeEncoder getEncoder() {
        return spark.getEncoder(Type.kQuadrature, 1024);
    }
}
