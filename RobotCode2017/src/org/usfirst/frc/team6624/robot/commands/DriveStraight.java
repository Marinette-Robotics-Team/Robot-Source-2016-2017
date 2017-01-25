package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	float driveTime;
	float driveSpeed;
	
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
    	
    	//throw exception if driveSpeed isnt between -1 and 1
    	if (Math.abs((double)driveSpeed) > 1) {
    		throw new IndexOutOfBoundsException("DriveSpeed must be between -1 and 1");
    	}
    	
    	//set instance vars
    	this.driveSpeed = driveSpeed;
    	this.driveTime = driveTime;
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.setLeftSpeed(driveSpeed);
    	Robot.drive.setRightSpeed(driveSpeed);
    	
    	Robot.drive.updateTrimInput();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	if (timer.get() > driveTime) {
    		Robot.drive.setLeftSpeed(0);
        	Robot.drive.setRightSpeed(0);
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
