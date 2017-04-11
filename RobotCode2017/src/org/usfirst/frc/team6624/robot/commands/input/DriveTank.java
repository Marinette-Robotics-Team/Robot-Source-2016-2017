package org.usfirst.frc.team6624.robot.commands.input;

import org.usfirst.frc.team6624.robot.OI;
import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 *This class is an input class for tank drive with the xbox controller
 *
 *Left joystick - control left drive train
 *Right joystick - control right drive train
 *
 *The issue with this control scheme is that it's difficult to drive completely straight
 */
public class DriveTank extends Command {

    public DriveTank() {
        super("DriveTank");
    	requires(Robot.drive);
    }

    
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
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }


    protected void interrupted() {
    }
}
