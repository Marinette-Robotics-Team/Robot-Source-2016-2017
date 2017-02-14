package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.OI;
import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTank extends Command {

    public DriveTank() {
        super("DriveTank");
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Gets Y axis values of xbox sticks
    	double stickLeft = OI.xbox.getRawAxis(OI.xboxLeftY);
    	double stickRight = OI.xbox.getRawAxis(OI.xboxRightY);
    	
    	//Sets motors to their scaled speed
    	Robot.drive.setLeftSpeed(-stickLeft, true);//inverse y
    	Robot.drive.setRightSpeed(-stickRight, true);
    	
    	
    	//Debug output
    	//System.out.println("Left Speed: " + -stickLeft);
    	//System.out.println("Right Speed: " + -stickRight);
    	
    	
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
