package org.usfirst.frc.team6624.robot.commands.test;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.commands.drive.DriveToCoords;
import org.usfirst.frc.team6624.robot.customClasses.Vector2;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *This was is test class for the DriveToCoords function
 *
 * It is designed to start with the robot poiting down the hallway outside the robotics room
 * it drives in a path down some hallways and returns to its starting position
 */
public class AutonomousTest extends CommandGroup {

    public AutonomousTest() {
    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	addSequential(new DriveToCoords(new Vector2(51, 0)));
    	addSequential(new DriveToCoords(new Vector2(51, 43)));
    	addSequential(new DriveToCoords(new Vector2(76, 43)));
    	addSequential(new DriveToCoords(new Vector2(51, 43)));
    	addSequential(new DriveToCoords(new Vector2(51, 0)));
    	addSequential(new DriveToCoords(new Vector2(0, 0)));
    	

    }
}
