package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.OI;
import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GetRotConst extends Command {

	float angle;
	
	float rotConst;
	
	Timer timer;
	
    public GetRotConst(float angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("GetRotConst");
    	requires(Robot.drive);
    	
    	this.angle = angle;
    	this.rotConst = 0;
    	
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (angle > 0) {
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
    	
    	Boolean finished = OI.xbox.getBButton();
    	
    	if (finished) {
    		rotConst = (float)(Math.pow(timer.get(), 2) / angle);
    		System.out.println("Rotation constant for speed: " + 0.5 + "and angle: " + angle +" is:" + rotConst + ".");
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
