package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurn extends Command {

	float degrees;
	
	float rotTime;
	
	/**
	 * @param degrees dumber of degrees to rotate to the left (negative for right)
	 */
    public DriveTurn(float degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("DriveTurn");
    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	this.degrees = degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyroscope.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (degrees > 0) {
    		Robot.drive.setRightSpeed(1.0);
    	}
    	else {
    		Robot.drive.setLeftSpeed(1.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	//get if robot has rotated through given degree measurement
    	Boolean finished = Math.abs( Robot.gyroscope.getRotation() ) >= Math.abs( degrees );
    	
    	if (finished) {
    		Robot.drive.setRightSpeed(0);
    		Robot.drive.setLeftSpeed(0);
    	}
    	
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
