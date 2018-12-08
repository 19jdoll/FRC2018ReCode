/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.Intake;

public class RunIntake extends Command {

  Intake mIntake = Intake.getInstance();

  public RunIntake() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    requires(mIntake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("RunIntake: Initialize");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    mIntake.setSpeed(RobotMap.INTAKE_SPEED);
    System.out.println("RunIntake: Execute");
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("RunIntake: End");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    mIntake.setSpeed(0.0);
    System.out.println("RunIntake: Interrupted");
  }
}
