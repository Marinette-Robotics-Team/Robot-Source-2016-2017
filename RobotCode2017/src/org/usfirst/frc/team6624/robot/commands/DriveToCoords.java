package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6634.robot.customClasses.Vector2;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This CommandGroup rotates the robot and drives to a given Vector2 
 * coordinate on the field using DriveStraightDistance and DriveTurn
 */
public class DriveToCoords extends CommandGroup {

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
    	
    	//add DriveTurn command to queue
    	addSequential(new DriveTurn(angle, true));
    	
    	//go to coordinate
    	//TODO: change from DriveStraight to DriveStraightDistance !!IMPORTANT
    	addSequential(new DriveStraight(3.0f, 0.6f));
    	
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
}
