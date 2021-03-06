/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.DriveArcadeMode;
import frc.robot.commands.DriveTankMode;
import frc.robot.commands.ReverseIntake;
import frc.robot.commands.RunIntake;
import frc.robot.subsystems.Drivebase;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  public static OI mInstance = null;

  Joystick mDriverJoystick, mOperatorJoystick;
  Button mDriverButtonA, mDriverButtonB, mDriverButtonX, mDriverButtonY, mDriverButtonLB, mDriverButtonRB;
  Button mOperatorButtonA, mOperatorButtonB, mOperatorButtonX, mOperatorButtonY, mOperatorButtonLB, mOperatorButtonRB;

  Drivebase mDrivebase = Drivebase.getInstance();

  public double mDriverLeftStickX = 0.0;
  public double mDriverLeftStickY = 0.0;
  public double mDriverRightStickX = 0.0;
  public double mDriverRightStickY = 0.0;

  public static OI getInstance() {
    if(mInstance == null) {
      mInstance = new OI();
    }
    return mInstance;
  }

  public OI() {
    mDriverJoystick = new Joystick(RobotMap.DRIVER_CONTROLLER_PORT);
    mOperatorJoystick = new Joystick(RobotMap.OPERATOR_CONTROLLER_PORT);
    
    mDriverButtonA = new JoystickButton(mDriverJoystick, RobotMap.XBOX_BUTTON_A_PORT);
    mDriverButtonRB = new JoystickButton(mDriverJoystick, RobotMap.XBOX_BUTTON_RB_PORT);
    mDriverButtonLB = new JoystickButton(mDriverJoystick, RobotMap.XBOX_BUTTON_LB_PORT);

    mOperatorButtonRB = new JoystickButton(mOperatorJoystick, RobotMap.XBOX_BUTTON_RB_PORT);
    mOperatorButtonLB = new JoystickButton(mOperatorJoystick, RobotMap.XBOX_BUTTON_LB_PORT);

    mDriverButtonLB.whenPressed(new DriveArcadeMode());
    mDriverButtonRB.whenPressed(new DriveTankMode());

    mOperatorButtonLB.whileHeld(new RunIntake());
    mOperatorButtonRB.whileHeld(new ReverseIntake());

  }

  public void run() {
    mDriverLeftStickX = mDriverJoystick.getRawAxis(RobotMap.XBOX_LEFT_STICK_AXIS_X);
    mDriverLeftStickY = mDriverJoystick.getRawAxis(RobotMap.XBOX_LEFT_STICK_AXIS_Y);
    mDriverRightStickX = mDriverJoystick.getRawAxis(RobotMap.XBOX_RIGHT_STICK_AXIS_X);
    mDriverRightStickY = mDriverJoystick.getRawAxis(RobotMap.XBOX_RIGHT_STICK_AXIS_Y);

    mDriverLeftStickX *= RobotMap.STICK_DAMPEN_PERCENTAGE;
    mDriverLeftStickY *= RobotMap.STICK_DAMPEN_PERCENTAGE;
    mDriverRightStickX *= RobotMap.STICK_DAMPEN_PERCENTAGE;
    mDriverRightStickY *= RobotMap.STICK_DAMPEN_PERCENTAGE;

    //System.out.println("Left Stick: " + mDriverLeftStickX + ",  " + mDriverLeftStickY);
    //System.out.println("Right Stick: " + mDriverRightStickX + ",  " + mDriverRightStickY);

  }
}
