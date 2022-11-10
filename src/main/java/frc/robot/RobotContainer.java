// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.DriveWithJoystickCommand;
import frc.robot.oi.CoDriverOI;
import frc.robot.oi.DriverOI;
import frc.robot.subsystems.CartridgeSubsystem;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.LauncherPIDSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ChassisSubsystem chassisSubsystem_ = new ChassisSubsystem();
  private final LauncherPIDSubsystem launcherPIDSubsystem_ = new LauncherPIDSubsystem();
  private final CartridgeSubsystem cartridgeSubsystem_ = new CartridgeSubsystem();
  private final HopperSubsystem hopperSubsystem_ = new HopperSubsystem();
  private DriverOI driverOI_ = null;
  private CoDriverOI coDriverOI_ = null;
  private PowerDistribution pdp = new PowerDistribution();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Set the scheduler to log Shuffleboard events for command initialize,
    // interrupt, finish
    CommandScheduler.getInstance()
        .onCommandInitialize(
            command ->
                Shuffleboard.addEventMarker(
                    "Command initialized", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance()
        .onCommandInterrupt(
            command ->
                Shuffleboard.addEventMarker(
                    "Command interrupted", command.getName(), EventImportance.kNormal));
    CommandScheduler.getInstance()
        .onCommandFinish(
            command ->
                Shuffleboard.addEventMarker(
                    "Command finished", command.getName(), EventImportance.kNormal));

    // Configure the button bindings
    configureButtonBindings();

    chassisSubsystem_.setDefaultCommand(new DriveWithJoystickCommand(chassisSubsystem_, driverOI_));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    driverOI_ = new DriverOI(Constants.DRIVER_OI, this);
    coDriverOI_ = new CoDriverOI(Constants.CODRIVE_OI, this);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
  }

  /**
   * retrieves the driver operator interface
   *
   * @return
   */
  public DriverOI getDriverOi() {
    return driverOI_;
  }

  /**
   * retrieves the chassis subsystem
   *
   * @return
   */
  public ChassisSubsystem getChassisSubsystem() {
    return chassisSubsystem_;
  }

  /**
   * retrieves cartridge subsystem
   *
   * @return
   */
  public CartridgeSubsystem getCartridgeSubsystem() {
    return cartridgeSubsystem_;
  }

  /**
   * retrieves launcher PID subsystem
   *
   * @return
   */
  public LauncherPIDSubsystem getLauncherPIDSubsystem() {
    return launcherPIDSubsystem_;
  }

  /**
   * retrieves hopper subsystem
   *
   * @return
   */
  public HopperSubsystem getHopperSubsystem() {
    return hopperSubsystem_;
  }

  /**
   * Use this to get the PDP for data logging.
   *
   * @return The PowerDistribution module.
   */
  public PowerDistribution getPdp() {
    return this.pdp;
  }

  /** Returns the driver. */
  public DriverOI getDriver() {
    return this.driverOI_;
  }

  /** Returns the driver. */
  public CoDriverOI getCoDriver() {
    return this.coDriverOI_;
  }
}
