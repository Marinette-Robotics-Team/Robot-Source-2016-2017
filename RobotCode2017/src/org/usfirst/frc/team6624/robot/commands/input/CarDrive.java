package org.usfirst.frc.team6624.robot.commands.input;

import org.usfirst.frc.team6624.robot.OI;
import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.RobotMap;

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


	
    public CarDrive() {
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Start of Main Driver Code
    	
    	//get axis values
    	double stickX = OI.xbox.getRawAxis(OI.xboxRightX);
    	double stickY = OI.xbox.getRawAxis(OI.xboxLeftY);
    	
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
    	
    	//Start of CO-Pilot code
    	//Expects value from -1 to +1
    	double JoysticX = OI.joystick.getRawAxis(OI.joystickX);
    	double JoysticY = OI.joystick.getRawAxis(OI.joystickY);
    	
    	
    	
    	double assistY = JoysticY * (RobotMap.ASSIST_DOWN_SCALE_FACTOR);
    	double assistX = JoysticX * (RobotMap.ASSIST_DOWN_SCALE_FACTOR);
    	
    	double leftAssist = 0;
    	double rightAssist = 0;
    	
    	
    	if(assistX<-RobotMap.ASSIST_X_THRESHOLD){
    		 leftAssist = assistX; 
    	}
    	else if (assistX> RobotMap.ASSIST_X_THRESHOLD){
    		 rightAssist = assistX;
    		
    	}
    	else{
    		leftAssist = 0;
    		rightAssist = 0;
    	}
    	
    	
    	
    	//set motors
    	Robot.drive.setLeftSpeed((-stickY + assistY) * (leftPower + leftAssist), true); //negative accounts for inverse y axis on xbox controls
    	Robot.drive.setRightSpeed((-stickY + assistY) * (rightPower + rightAssist), true);
    	
    	
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
