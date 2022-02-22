package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder.Type;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
    private CANSparkMax left = new CANSparkMax(1, MotorType.kBrushed);
    private CANSparkMax right = new CANSparkMax(2, MotorType.kBrushed);

    public Climber() {}

    private void set(CANSparkMax spark, double val) {
        spark.set(val * 0.25);
    }

    public void setLeft(double val) {
        set(left, val);
    }

    public void setRight(double val) {
        set(right, val);
    }

    private RelativeEncoder getEncoder(CANSparkMax spark) {
        return spark.getEncoder(Type.kQuadrature, 1024);
    }

    public RelativeEncoder getLeftEncoder() {
        return getEncoder(left);
    }

    public RelativeEncoder getRighEncoder() {
        return getEncoder(right);
    }
}
