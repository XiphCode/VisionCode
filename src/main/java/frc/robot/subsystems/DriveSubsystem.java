// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveSubsystem extends SubsystemBase {
  private WPI_TalonSRX talonFL = new WPI_TalonSRX(DriveConstants.TALON_FL_ID);
  private WPI_TalonSRX talonFR = new WPI_TalonSRX(DriveConstants.TALON_FR_ID);
  private WPI_TalonSRX talonBL = new WPI_TalonSRX(DriveConstants.TALON_BL_ID);
  private WPI_TalonSRX talonBR = new WPI_TalonSRX(DriveConstants.TALON_BR_ID);
  private MecanumDrive mechDrive = new MecanumDrive(talonFL, talonBL, talonFR, talonBR);

  public DriveSubsystem() {
    mechDrive.setMaxOutput(DriveConstants.DRIVE_SPEED);
  }

  @Override
  public void periodic() {
    // Add logging of encoder values here
  }

  public void driveCartesian(double driveVal, double strafeVal, double rotateVal) {
    SmartDashboard.putNumber("Drive", driveVal);
    SmartDashboard.putNumber("Strafe", strafeVal);
    SmartDashboard.putNumber("Turn", rotateVal);
    mechDrive.driveCartesian(rotateVal, strafeVal, driveVal);
  }
}
