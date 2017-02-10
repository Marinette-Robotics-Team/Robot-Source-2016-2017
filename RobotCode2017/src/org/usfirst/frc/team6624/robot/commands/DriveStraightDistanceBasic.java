package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightDistanceBasic extends Command {

	
	double distance;
	
    public DriveStraightDistanceBasic(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.setLeftSpeed(0.7);
    	Robot.drive.setRightSpeed(0.7);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Left Distance: " + Robot.drive.leftEncoder.getDistance());
    	System.out.println("Right Distance: " + Robot.drive.rightEncoder.getDistance());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Robot.drive.leftEncoder.getDistance() >= distance && Robot.drive.rightEncoder.getDistance() >= distance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setLeftSpeed(0);
    	Robot.drive.setRightSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.setLeftSpeed(0);
    	Robot.drive.setRightSpeed(0);
    }
}
