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
	
	float speed;
	
	Timer timer;
	
    public GetRotConst(float angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("GetRotConst");
    	requires(Robot.drive);
    	
    	this.angle = angle;
    	this.rotConst = 0;
    	this.speed = 0.7f;
    	
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	
    	if (angle > 0) {
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
    	
    	Boolean finished = OI.xbox.getBButton();
    	
    	if (finished) {
    		System.out.println(timer.get());
    		//rotConst = (float)(Math.pow(timer.get(), 2) / angle);
    		rotConst = (float)(angle / timer.get());
    		System.out.println("Rotation constant for speed: " + speed + " and angle: " + angle +" is: " + rotConst + ".");
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
