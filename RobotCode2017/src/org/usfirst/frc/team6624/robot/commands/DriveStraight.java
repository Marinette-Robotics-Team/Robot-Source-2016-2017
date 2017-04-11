package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	
	final float DECELERATE_COEFFICIENT = 0.98f;
	final float DEGREE_THRESHOLD = 2; //number of degrees to correct after
	
	float driveTime;
	float driveSpeed;
	float driveSpeedLeft;
	float driveSpeedRight;
	
	Timer timer;
	
	/**
	 * @param driveTime number of seconds to drive forward for
	 * @param driveSpeed speed to set motors, from -1 to 1
	 */
    public DriveStraight(float driveTime, float driveSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("DriveForward");
    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	//throw exception if driveSpeed isnt between -1 and 1
    	if (Math.abs((double)driveSpeed) > 1) {
    		throw new IndexOutOfBoundsException("DriveSpeed must be between -1 and 1");
    	}
    	
    	//set instance vars
    	this.driveSpeed = driveSpeed;
    	this.driveTime = driveTime;
    	
    	driveSpeedLeft = driveSpeed;
    	driveSpeedRight = driveSpeed;
    	
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	Robot.gyroscope.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentAngle = Robot.gyroscope.getRotation();
    	
    	System.out.println(currentAngle);
    	
    	// Gyro Assisted straight driving
    	
    	Robot.drive.setLeftSpeed(driveSpeedLeft, false);
    	Robot.drive.setRightSpeed(driveSpeedRight, false);
    	
    	if  (currentAngle > DEGREE_THRESHOLD) {
    		driveSpeedRight = DECELERATE_COEFFICIENT * driveSpeed;
    	}
    	else if (currentAngle <= 0) {
    		driveSpeedRight = driveSpeed;
    	}
    	
    	if(currentAngle < -DEGREE_THRESHOLD) {
    		driveSpeedLeft = DECELERATE_COEFFICIENT * driveSpeed;
    	}
    	else if (currentAngle >= 0){
    		driveSpeedLeft = driveSpeed;
    	}
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	if (timer.get() > driveTime) {
    		Robot.drive.setLeftSpeed(0, false);
        	Robot.drive.setRightSpeed(0, false);
    	}
    	
        return timer.get() > driveTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
