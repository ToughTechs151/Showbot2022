/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.oi;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.ChangeLauncherSpeedCommand;
import frc.robot.commands.DisableLauncherCommand;
import frc.robot.commands.IncrementHopperCommand;
import frc.robot.commands.ReleaseBallPerSecondThroughHopperCommandGroup;
import frc.robot.commands.ReleaseBallThroughCartridgeCommandGroup;

/**
 * CoDriver OI Controls
 */
public class CoDriverOI extends OI {
    private static final double LAUNCHER_SPEED = 0;
    private RobotContainer robotContainer_ = null;

    public CoDriverOI(int channel, RobotContainer robotContainer) {
        super(channel);
        robotContainer_ = robotContainer;
        
        start = new JoystickButton(joystick, Constants.START);
        start.whenPressed(new IncrementHopperCommand(0.35, robotContainer));

        back = new JoystickButton(joystick, Constants.BACK);
        back.whenPressed(new IncrementHopperCommand(-0.35, robotContainer));

        rightBumper = new JoystickButton(joystick, Constants.RIGHT_BUMPER);
        rightBumper.whileHeld(new ReleaseBallPerSecondThroughHopperCommandGroup(robotContainer));
    
        leftBumper = new JoystickButton(joystick, Constants.LEFT_BUMPER);    
        leftBumper.whenPressed(new ReleaseBallThroughCartridgeCommandGroup());

        a = new JoystickButton(joystick, Constants.A);
        // rpm
        a.whenPressed(new ChangeLauncherSpeedCommand(Constants.LAUNCHER_SPEED, robotContainer));

        b = new JoystickButton(joystick, Constants.B);
        b.whenPressed(new DisableLauncherCommand(robotContainer));

        b = new JoystickButton(joystick, Constants.B);
        y = new JoystickButton(joystick, Constants.Y);
        x = new JoystickButton(joystick, Constants.X);
    
        leftJoystick = new JoystickButton(joystick, Constants.LEFT_JOYSTICK);
        rightJoystick = new JoystickButton(joystick, Constants.RIGHT_JOYSTICK);
    }
}