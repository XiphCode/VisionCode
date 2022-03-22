package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder.Type;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {
    private CANSparkMax left = new CANSparkMax(ClimberConstants.LEFT_SPARK, MotorType.kBrushed);
    private CANSparkMax right = new CANSparkMax(ClimberConstants.RIGHT_SPARK, MotorType.kBrushed);
    private RelativeEncoder leftEncoder = getEncoder(left);
    private Encoder rightEncoder = new Encoder(
        ClimberConstants.RIGHT_ENCODER_PIN_A, ClimberConstants.RIGHT_ENCODER_PIN_B
    );

    public ClimberSubsystem() {}

    private void set(MotorController spark, double val) {
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

    public Encoder getRightEncoder() {
        return rightEncoder;
    }
}
