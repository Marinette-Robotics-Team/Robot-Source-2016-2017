package org.usfirst.frc.team6624.robot.commands.input;

import org.usfirst.frc.team6624.robot.OI;
import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 *This class handles the CarDrive control scheme.
 *
 *How this control scheme works is :
 *	Left joystick y axis: Control robot speed
 *	Right joystick x axis: Turn robot left/right
 *
 *It handles pretty similarly to a car.
 *
 *It is easy to drive straight, and turns are jsut as accurate as tank drive.
 *This drive scheme is reccomended over tank drive.
 *
 *
 */
public class CarDrive extends Command {

	//set axes from each joystick
	int xAxis = OI.xboxRightX;
	int yAxis = OI.xboxLeftY; 
	
    public CarDrive() {
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//get axis values
    	double stickX = OI.xbox.getRawAxis(xAxis);
    	double stickY = OI.xbox.getRawAxis(yAxis);
    	
    	//scale right and left motor power by x axis
    	double rightPower;
    	double leftPower;
    	
    	if (stickX > 0) {
    		rightPower = 1 - 2 * Math.pow(stickX, 5); //apply curve to turning to make it easier to control
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
    	Robot.drive.setLeftSpeed(-stickY * leftPower, true); //negative accounts for inverse y axis on xbox controls
    	Robot.drive.setRightSpeed(-stickY * rightPower, true);
    }

    protected boolean isFinished() {
        return false;
    }

    //Turn off motors when interrupted
    protected void end() {
    	Robot.drive.setLeftSpeed(0, false);
    	Robot.drive.setRightSpeed(0, false);
    }

    //Turn motors off when disaster strikes
    protected void interrupted() {
    	Robot.drive.setLeftSpeed(0, false);
    	Robot.drive.setRightSpeed(0, false);
    }
}
