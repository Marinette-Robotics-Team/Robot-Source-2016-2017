package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurn extends Command {

	float degrees;
	float rotConstant;
	
	float rotTime;
	
	Timer timer;
	/**
	 * @param degrees dumber of degrees to rotate to the left (negative for right)
	 */
    public DriveTurn(float degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("DriveTurn");
    	requires(Robot.drive);
    	
    	this.degrees = degrees;
    	rotConstant = 1;
    	
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//ratios
    	rotTime = rotConstant * degrees;
    	
    	//sqrt ratios
    	rotTime = (float)Math.sqrt(rotConstant * degrees);
    	
    	//set timer
    	timer.reset();
    	timer.start();
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
    	
    	if (timer.hasPeriodPassed(rotTime)) {
    		Robot.drive.setRightSpeed(0);
    		Robot.drive.setLeftSpeed(0);
    	}
    	
        return timer.hasPeriodPassed(rotTime);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
