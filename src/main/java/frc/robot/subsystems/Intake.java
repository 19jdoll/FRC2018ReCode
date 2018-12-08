/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static Intake mInstance;

  VictorSPX mLeftIntakeMotor;
  VictorSPX mRightIntakeMotor;

  DigitalInput mIntakeLimitSwitch;

  public static Intake getInstance() {
    if(mInstance == null) {
      mInstance = new Intake();
    }
    return mInstance;
  }

  private Intake() {
    mLeftIntakeMotor = new VictorSPX(RobotMap.LEFT_INTAKE_CAN_PORT);
    mRightIntakeMotor = new VictorSPX(RobotMap.RIGHT_INTAKE_CAN_PORT);

    mIntakeLimitSwitch = new DigitalInput(RobotMap.INTAKE_LIMIT_SWITCH_PORT);
    
    mLeftIntakeMotor.setInverted(true);
  }

  public void setSpeed(double speed) {
    if(mIntakeLimitSwitch.get()) {
      speed = Math.min(speed, 0.0);
    }
    mLeftIntakeMotor.set(ControlMode.PercentOutput, speed);
    mRightIntakeMotor.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
