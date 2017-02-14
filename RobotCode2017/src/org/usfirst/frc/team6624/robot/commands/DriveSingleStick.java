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
    	
    	//scale right and left motor power by x axis
    	double rightPower;
    	double leftPower;
    	
    	if (stickX > 0) {
    		rightPower = 1 - 2 * Math.pow(stickX, 5);
    		leftPower = 1;
    	}
    	else if (stickX < 0) {
    		rightPower = 1;
    		leftPower = 1 + 2 * Math.pow(stickX, 5);
    	}
    	else {
    		leftPower = 1;
    		rightPower = 1;
    	}
    	
    	
    	//set motors
    	Robot.drive.setLeftSpeed(-stickY * leftPower, true); //negative accounts for inverse y axis
    	Robot.drive.setRightSpeed(-stickY * rightPower, true);
    	
    	//System.out.println("Left Speed: " + -stickY * leftPower);
    	//System.out.println("Right Speed: " + -stickY * rightPower);
    	
    	Robot.drive.updateTrimInput();
    	
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
