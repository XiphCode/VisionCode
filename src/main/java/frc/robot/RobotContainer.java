// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.ControllerConstants;
import frc.robot.commands.ArcadeDriveCmd;
import frc.robot.commands.ArmControlCmd;
import frc.robot.commands.BallTurnCmd;
import frc.robot.commands.ClimberControlCmd;
import frc.robot.commands.ClimberPIDCmd;
import frc.robot.commands.DriveCmd;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.LoggerCmd;
import frc.robot.commands.ReplayPosesCmd;
import frc.robot.commands.Turn180Command;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.InnerIntakeSubsystem;
import frc.robot.subsystems.OuterIntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final ClimberSubsystem climber = new ClimberSubsystem();
  private final InnerIntakeSubsystem innerIntake = new InnerIntakeSubsystem();
  private final OuterIntakeSubsystem outerIntake = new OuterIntakeSubsystem();
  private final ArmSubsystem arm = new ArmSubsystem();
  private final XboxController driverController = new XboxController(
    ControllerConstants.DRIVER_PORT
  );
  private final XboxController coDriverController = new XboxController(
    ControllerConstants.CODRIVER_PORT
  );

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure driving command
    driveSubsystem.setDefaultCommand(
      new ArcadeDriveCmd(
        driveSubsystem, 
        () -> -driverController.getLeftY(), 
        () -> driverController.getLeftX(), 
        () -> driverController.getRightX(),
        () -> driverController.getRightBumper()
      )
    );

    climber.setDefaultCommand(
      new ClimberControlCmd(climber, () -> coDriverController.getRightY())
    );

    arm.setDefaultCommand(
      new ArmControlCmd(arm, () -> coDriverController.getLeftY())
    );

    // Schedule logging command to run in the background
    CommandScheduler.getInstance().schedule(new LoggerCmd(climber, arm));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Driver
    new JoystickButton(driverController, XboxController.Button.kA.value)
      .whenPressed(new InstantCommand(driveSubsystem::resetGyroAngle));
    new JoystickButton(driverController, XboxController.Button.kX.value)
      .whenPressed(new Turn180Command(driveSubsystem).withTimeout(5));
    new JoystickButton(driverController, XboxController.Button.kB.value)
      .whileActiveOnce(new BallTurnCmd(driveSubsystem), true);
    
    // Co-Driver
    new JoystickButton(coDriverController, XboxController.Button.kY.value)
      .whenPressed(new ClimberPIDCmd(climber, 1).withTimeout(5));
    new JoystickButton(coDriverController, XboxController.Button.kA.value)
      .whenPressed(new ClimberPIDCmd(climber, -1).withTimeout(5));
    new JoystickButton(coDriverController, XboxController.Button.kBack.value)
      .whenPressed(new InstantCommand(() -> {
        System.out.println("Reset climber encoder");
        climber.getLeftEncoder().setPosition(0);
      }));
    new JoystickButton(coDriverController, XboxController.Button.kLeftBumper.value)
      .whileActiveOnce(new IntakeCommand(innerIntake, 1));
    new JoystickButton(coDriverController, XboxController.Button.kRightBumper.value)
      .whileActiveOnce(new IntakeCommand(outerIntake, 1));
    new Trigger(() -> coDriverController.getLeftTriggerAxis() > 0.25)
      .whileActiveOnce(new IntakeCommand(innerIntake, -1));
    new Trigger(() -> coDriverController.getRightTriggerAxis() > 0.25)
      .whileActiveOnce(new IntakeCommand(outerIntake, -1));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
      new DriveCmd(driveSubsystem, 0.5, 0, 0).withTimeout(1),
      new DriveCmd(driveSubsystem, 0, 0, 0.5).withTimeout(0.5),
      new DriveCmd(driveSubsystem, 0, 0, 0).withTimeout(0.5),
      new ReplayPosesCmd(driveSubsystem, true)
    );
  }
}
