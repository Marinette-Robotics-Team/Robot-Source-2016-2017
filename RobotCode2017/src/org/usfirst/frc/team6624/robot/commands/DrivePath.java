package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.commands.drive.DriveToCoords;
import org.usfirst.frc.team6624.robot.customClasses.Path;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This class is a CommandGroup that takes a Path variable as an object, and drives the robot along
 * the path, by driving to each consecutive Vector2
 * 
 * This works by driving to the point, turning, and driving to the next point.
 * 
 * This command does not utilize curves (straight paths only)
 * 
 */
public class DrivePath extends CommandGroup {

	/**
	 * Command that makes the robot drive along lines between the waypoints given py the path
	 * @param path The Path to drive along
	 */
    public DrivePath(Path path) {

    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	//remove redundant Vector2s
    	path.simplify(Robot.drive.position);
    	
    	//queue each waypoint
    	for (int i = 0; i < path.size(); i++) {
    		addSequential(new DriveToCoords(path.get(i)));
    	}

    	
    }
}
