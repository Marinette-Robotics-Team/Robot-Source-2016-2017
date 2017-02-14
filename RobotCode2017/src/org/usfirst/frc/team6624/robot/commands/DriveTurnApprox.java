package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurnApprox extends Command {
	
	float degrees;
	
	float rotConst;
	
	float rotTime;
	
	float speed;
	
	Timer timer;

    public DriveTurnApprox(float degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("DriveTurnApprox");
    	requires(Robot.drive);
    	
    	this.degrees = degrees;
    	this.rotConst = 135.25f;
    	this.speed = 0.7f;
    	
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	
    	rotTime = (float)((1 / rotConst) * Math.abs(degrees));
    	
    	if (degrees > 0) {
    		Robot.drive.setRightSpeed(speed, false);
    		Robot.drive.setLeftSpeed(-speed, false);
    	}
    	else {
    		Robot.drive.setLeftSpeed(speed, false);
    		Robot.drive.setRightSpeed(-speed, false);
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	Boolean finished = (timer.get() >= rotTime);
    	
    	if (finished) {
    		Robot.drive.setRightSpeed(0, false);
    		Robot.drive.setLeftSpeed(0, false);
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
