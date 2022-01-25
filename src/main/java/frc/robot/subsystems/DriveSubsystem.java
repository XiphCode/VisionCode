// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveSubsystem extends SubsystemBase {
  private WPI_TalonSRX talonFL = new WPI_TalonSRX(DriveConstants.TALON_FL_ID);
  private WPI_TalonSRX talonFR = new WPI_TalonSRX(DriveConstants.TALON_FR_ID);
  private CANSparkMax sparkBL = new CANSparkMax(DriveConstants.SPARK_BL_ID, MotorType.kBrushed);
  private CANSparkMax sparkBR = new CANSparkMax(DriveConstants.SPARK_BR_ID, MotorType.kBrushed);
  private MecanumDrive drive = new MecanumDrive(talonFL, sparkBL, talonFR, sparkBR);

  public DriveSubsystem() {
  }

  @Override
  public void periodic() {
    // Add logging of encoder values here
  }

  public void driveCartesian(double ySpeed, double xSpeed, double zRotation) {
    drive.driveCartesian(ySpeed, xSpeed, zRotation);
  }
}
