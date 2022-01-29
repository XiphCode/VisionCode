// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Constants.DriveConstants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

public class DriveSubsystem extends SubsystemBase {
  private WPI_TalonSRX talonFL = new WPI_TalonSRX(DriveConstants.TALON_FL_ID);
  private WPI_TalonSRX talonFR = new WPI_TalonSRX(DriveConstants.TALON_FR_ID);
  private WPI_TalonSRX talonBL = new WPI_TalonSRX(DriveConstants.TALON_BL_ID);
  private WPI_TalonSRX talonBR = new WPI_TalonSRX(DriveConstants.TALON_BR_ID);
  private MecanumDrive mechDrive = new MecanumDrive(talonFL, talonBL, talonFR, talonBR);
  private final AHRS ahrs = new AHRS(SPI.Port.kMXP);
  private double turnAuto = 0;

  public DriveSubsystem() {
    mechDrive.setMaxOutput(DriveConstants.DRIVE_SPEED);
    talonFR.setInverted(true);
    talonBR.setInverted(true);
  }

  @Override
  public void periodic() {
    // Add logging of encoder values here
  }

  public void resetGyroAngle() {
    ahrs.zeroYaw();
  }

  public double getGyroAngle() {
    return ahrs.getAngle();
  }

  public double gyroPidGet() {
    return ahrs.getYaw();
  }

  public void setTurnAuto(double turnAuto) {
    this.turnAuto = turnAuto;
  }

  public void driveCartesian(double driveVal, double strafeVal, double rotateInput) {
    // Prefer the rotateInput if there is any.
    double rotateVal = Math.abs(rotateInput) >= 0.1 ? rotateInput : turnAuto;
    SmartDashboard.putNumber("Drive", driveVal);
    SmartDashboard.putNumber("Strafe", strafeVal);
    SmartDashboard.putNumber("Turn", rotateVal);
    SmartDashboard.putNumber("Turn Auto", turnAuto);

    mechDrive.driveCartesian(driveVal, strafeVal, rotateVal, ahrs.getAngle());
  }
}
