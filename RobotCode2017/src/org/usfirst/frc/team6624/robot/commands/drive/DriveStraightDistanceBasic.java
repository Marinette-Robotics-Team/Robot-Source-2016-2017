package org.usfirst.frc.team6624.robot.commands.drive;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 *This command drives the robot straight a certain distance.
 *
 *The only argument it takes is the distance to travel (ft).
 *
 *It moves forward at a constant speed, and then stops as soon as the encoders report a distance greater
 *than or equal to than the target distance.
 *
 *It's not very sophisticated, but it's nice for testing the encoders or if tests involving moving the robot
 *at a constant speed are needed.
 *
 *If you're looking for a good command to drive the robot a certain distance with higher accuracy and
 *customizability, use DriveStrightDistanceBasic
 *
 */
public class DriveStraightDistanceBasic extends Command {

	
	double distance;
	
    public DriveStraightDistanceBasic(double distance) {
    	requires(Robot.drive);
    	
    	this.distance = distance;
    }

    // Turn on motors at the start of the command
    protected void initialize() {
    	Robot.drive.setLeftSpeed(0.2, false);
    	Robot.drive.setRightSpeed(0.2, false);
    	
    	Robot.drive.leftEncoder.reset();
    	Robot.drive.rightEncoder.reset();
    }

    // Print left and right encoder distances for debug reasons
    protected void execute() {
    	System.out.println("Left Distance: " + Robot.drive.leftEncoder.getDistance());
    	System.out.println("Right Distance: " + Robot.drive.rightEncoder.getDistance());
    }

    //End the command when the target distance is reached/passed
    protected boolean isFinished() {
    	return (Math.abs(Robot.drive.leftEncoder.getDistance()) >= distance && Math.abs(Robot.drive.rightEncoder.getDistance()) >= distance);
    }

    //Turn off motors
    protected void end() {
    	Robot.drive.setLeftSpeed(0, false);
    	Robot.drive.setRightSpeed(0, false);
    }

    //Turn off motors if interrupt
    protected void interrupted() {
    	Robot.drive.setLeftSpeed(0, false);
    	Robot.drive.setRightSpeed(0, false);
    }
}
