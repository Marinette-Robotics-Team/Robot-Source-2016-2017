package org.usfirst.frc.team6624.robot.commands.drive;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.customClasses.Vector2;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This CommandGroup rotates the robot and drives to a given Vector2 
 * coordinate on the field using DriveStraightDistance and DriveTurn
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
    	double vel = getDriveAcceleration(distance);
    	
    	//add DriveTurn command to queue
    	addSequential(new DriveTurn(angle));
    	
    	//go to coordinate
    	addSequential(new DriveStraightDistance(distance, vel, accel, true, angle));
    	
    	//set current position to destination
    	Robot.drive.position = destination;
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    
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
	    //get arctan of distance between current and destination positions
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