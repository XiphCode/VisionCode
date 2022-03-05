package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder.Type;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {
    private CANSparkMax left = new CANSparkMax(ClimberConstants.LEFT_SPARK, MotorType.kBrushed);
    private CANSparkMax right = new CANSparkMax(ClimberConstants.RIGHT_SPARK, MotorType.kBrushed);
    private RelativeEncoder leftEncoder = getEncoder(left);
    private RelativeEncoder rightEncoder = getEncoder(right);

    public ClimberSubsystem() {}

    private void set(CANSparkMax spark, double val) {
        System.out.println("Spark " + spark.getDeviceId() + " set to " + val);
        spark.set(val * ClimberConstants.POWER);
    }

    public void setLeft(double val) {
        set(left, val);
    }

    public void setRight(double val) {
        set(right, val);
    }

    private RelativeEncoder getEncoder(CANSparkMax spark) {
        return spark.getEncoder(Type.kQuadrature, ClimberConstants.ENCODER_COUNTS_PER_REV);
    }

    public RelativeEncoder getLeftEncoder() {
        return leftEncoder;
    }

    public RelativeEncoder getRightEncoder() {
        return rightEncoder;
    }
}
