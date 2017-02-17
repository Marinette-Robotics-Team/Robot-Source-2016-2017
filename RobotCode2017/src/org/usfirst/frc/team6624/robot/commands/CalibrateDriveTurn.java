package org.usfirst.frc.team6624.robot.commands;


import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CalibrateDriveTurn extends Command {

	static double P;
	static double I;
	static double D;
	
	static Boolean absolute;
	
	static double setPoint;
	
    public CalibrateDriveTurn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	P = 0;
    	I = 0;
    	D = 0;
    	
    	setPoint = 0;
    	absolute = false;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putNumber("P", P);
    	SmartDashboard.putNumber("I", I);
    	SmartDashboard.putNumber("D", D);
    	SmartDashboard.putBoolean("abs", absolute);
    	SmartDashboard.putNumber("SetPoint", setPoint);
    	SmartDashboard.putData("Save", new SaveData());
    	SmartDashboard.putData("Run", new RunCalibrateDriveTurn());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
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
