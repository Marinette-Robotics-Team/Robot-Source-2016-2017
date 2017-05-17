package org.usfirst.frc.team6624.robot.commands.drive;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.customClasses.Vector2;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This CommandGroup queues a combination of DriveTurn and DriveStraightDistance to move the robot
 * to a given Vector2 coordinate relative to its current position.
 * 
 * It takes one argument:
 * 		- Vector2 destination - the destination coordinate to drive to
 * 
 * This command uses the DriveStraightDistance command to drive from the current position do the 
 * destination position, which requires an acceleration argument and a cruising velocity argument
 * 
 * 
 * 
 * 
 */
public class DriveToCoords extends CommandGroup {

	final double DRIVE_SPEED_ACCELERATION = 4; // drive max cruising speed (ft/s) and acceleration (ft/s^2)
	
	//if high, faseter less acc, lower slower, more acc
	final double A_OVER_V = 4;
	
	Vector2 currentPosition;
	
	Vector2 destination;
	
	CommandState currentState;
	
	public static enum CommandState {
		waiting,
		turning,
		driving,
		done
	}
	
	
    public DriveToCoords(Vector2 destination) {
    	
    	requires(Robot.drive);
		requires(Robot.gyroscope);
		requires(Robot.accel);
		
		
    	
    	this.destination = destination;
    	this.currentPosition = Robot.drive.position;
    	
    	currentState = CommandState.waiting;
    	
    	double angle = calculateAngle();
    	double distance = calcualteDistance();
    	double accel = getDriveAcceleration(distance);
    	double vel = getDriveVelocity(distance);
    	
    	//add DriveTurn command to queue
    	addSequential(new DriveTurn(angle));
    	
    	//go to coordinate
    	addSequential(new DriveStraightDistance(distance, vel, accel, true, angle));
    	
    	//set current position to destination
    	Robot.drive.position = destination;
    	
    }
    
    /**
     * Returns the current state of the DriveToCoords command
     */
    public CommandState getState() {
    	return currentState;
    }
    
    /**
     * Calculates angle to rotate to in order to drive to destination coords
     */
    private double calculateAngle() {
	    //get arctan of vector between current and destination positions
    	double arctan = Math.atan2((destination.Y - currentPosition.Y), (destination.X - currentPosition.X)); //all hail atan2
    	
    	return Math.toDegrees(arctan);
    }
    
    /**
     * Calculates distance between current position and destination
     */
    private double calcualteDistance() {
    	return Vector2.getDistance(currentPosition, destination);
    }

    /**
     * Gets appropriate drive acceleration and maximum speed 
     * @param distance distance to be driven
     * @return the drive acceleration and maximum speed
     */
    private double getDriveAcceleration(double distance) {
    	//this function ensures that v^2/a is always less than distance, so DriveStriaghtDistance never throws an
    	//IllegalArgumentException
    	
    	double c = DRIVE_SPEED_ACCELERATION;
    	double k = Math.PI / (2 * c);
    	double x = distance;
    	
    	return ((2 * c) / Math.PI) * Math.atan(k * x);
    	
    }
    
    private double getDriveVelocity(double distance) {
    	return getDriveAcceleration(distance) / A_OVER_V;
    }
}
