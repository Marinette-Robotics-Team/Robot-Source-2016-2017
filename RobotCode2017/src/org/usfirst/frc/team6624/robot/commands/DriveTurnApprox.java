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
	
	Timer timer;

    public DriveTurnApprox(float degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("DriveTurnApprox");
    	requires(Robot.drive);
    	
    	this.degrees = degrees;
    	this.rotConst = 1;
    	
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	
    	rotTime = (float)Math.sqrt(rotConst * degrees);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (degrees > 0) {
    		Robot.drive.setRightSpeed(0.5);
    		Robot.drive.setLeftSpeed(-0.5);
    	}
    	else {
    		Robot.drive.setLeftSpeed(0.5);
    		Robot.drive.setRightSpeed(-0.5);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	Boolean finished = (timer.get() >= rotTime);
    	
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
