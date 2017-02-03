package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.subsystems.Gyroscope;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurn extends Command {
	
	//drive speed for rotation
	final float ROTATE_SPEED = 0.6f;
	
	final double ANGLE_RANGE = 5;

	double degrees;
	
	int rotateDirection;
	
	Boolean absoluteRotation;
	
	/**
	 * @param degrees dumber of degrees to rotate to the left (negative for right)
	 * @param absoluteRotation toggle of whether to rotate relatively from current position (default) or rotate to gloabl angle
	 */
    public DriveTurn(double degrees, Boolean absoluteRotation) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("DriveTurn");
    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	this.degrees = Gyroscope.simplifyAngle(degrees);
    	this.absoluteRotation = absoluteRotation;
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyroscope.reset();
    	
    	//calcualte which direction to rotate
    	if (absoluteRotation) {
    		
    		double current = Gyroscope.simplifyAngle( Robot.gyroscope.getGlobalRotation() );
    		
    		
    		if (Math.max(degrees, current) - Math.min(degrees, current) <= 180) {
    			if (Math.min(degrees, current) == current) {
    				rotateDirection = 1;
    			}
    			else {
    				rotateDirection = -1;
    			}
    		}
    		else {
    			if (Math.min(degrees, current) == current)  {
    				rotateDirection = -1;
    			}
    			else {
    				rotateDirection = 1;
    			}
    		}
    	}
    	else {
	    	if (degrees > 0)
	    		rotateDirection = 1;
	    	else
	    		rotateDirection = -1;
    	}
    	
    	Robot.drive.setRightSpeed(ROTATE_SPEED * rotateDirection);
		Robot.drive.setLeftSpeed(-ROTATE_SPEED * rotateDirection);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	double rotation;
    	
    	//set rotation based on angle settings
    	if (absoluteRotation)
    		rotation = Robot.gyroscope.getGlobalRotation();
    	else
    		rotation = Robot.gyroscope.getRotation();
    	
    	System.out.println("curr: " + Gyroscope.simplifyAngle(rotation) + "\n goal: " + degrees);
    	
    	//get if robot has rotated through given degree measurement
    						//set detection region
    	Boolean finished = (Gyroscope.simplifyAngle(rotation) * rotateDirection >= Math.abs( degrees ) - (ANGLE_RANGE * rotateDirection) && Gyroscope.simplifyAngle(rotation) * rotateDirection <= Math.abs( degrees ) + (ANGLE_RANGE * rotateDirection) );
    	
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
