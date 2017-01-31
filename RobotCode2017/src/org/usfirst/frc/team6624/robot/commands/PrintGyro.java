package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrintGyro extends Command {
	
	Boolean constantPrint;

    public PrintGyro(Boolean constantPrint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gyroscope);
    	
    	this.constantPrint = constantPrint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double rot = Robot.gyroscope.getGlobalRotation();
    	if (constantPrint) {
    		System.out.println("The rotation is: " + rot);
    	}
    	
    	if (Robot.gyroscope.gyro.isCalibrating()) {
    		System.out.println("WARNING: Gyroscope is still calibrating");
    	}
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
