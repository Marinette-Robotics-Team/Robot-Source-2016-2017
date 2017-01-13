package org.usfirst.frc.team6624.robot.commands;


import org.usfirst.frc.team6624.robot.OI;
import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveSingleStick extends Command {

	protected int xAxis;
	protected int yAxis;
	
	
	
    public DriveSingleStick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("DriveSingleStick");
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	xAxis = OI.xboxLeftX;
    	yAxis = OI.xboxLeftY;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//get axis values (0 - 255)
    	double stickX = OI.xbox.getRawAxis(xAxis);
    	double stickY = OI.xbox.getRawAxis(yAxis);
    	
    	//scale from -1 to 1
    	double scaledX = (stickX - 127) / 127;
    	double scaledY = (stickY - 127) / 127;
    	
    	//scale right and left motor power by x axis
    	double rightPower;
    	double leftPower;
    	
    	if (scaledX < 0) {
    		rightPower = 1 + scaledX;
    		leftPower = 1;
    	}
    	else if (scaledX > 0) {
    		rightPower = 1;
    		leftPower = 1 - scaledX;
    	}
    	else {
    		leftPower = 1;
    		rightPower = 1;
    	}
    	
    	
    	//set motors
    	Robot.drive.setLeftSpeed(scaledY * leftPower);	
    	Robot.drive.setRightSpeed(scaledY * rightPower);
    	
    	System.out.println("Left Speed: " + scaledY * leftPower);
    	System.out.println("Right Speed: " + scaledY * rightPower);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}