package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.IO;
import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbRope extends Command {

	public static double climbingSpeed = 1;
    public ClimbRope() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.roperclimberr);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (IO.xbox.getYButton())//ONLY climb if Y pressed
    		(Robot.roperclimberr).climb(climbingSpeed);
    	else
    		(Robot.roperclimberr).climb(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	(Robot.roperclimberr).climb(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	(Robot.roperclimberr).climb(0);
    }
}
