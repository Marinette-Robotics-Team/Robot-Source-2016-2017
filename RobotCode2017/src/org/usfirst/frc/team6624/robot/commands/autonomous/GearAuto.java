package org.usfirst.frc.team6624.robot.commands.autonomous;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.commands.drive.DrivePath;
import org.usfirst.frc.team6624.robot.customClasses.Path;
import org.usfirst.frc.team6624.robot.customClasses.Vector2;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This CommandGroup is the command that is run during autonomous.
 * 
 * It takes single argument:
 * 	-int peg - the index of the peg to path to (see constructor javadoc)
 * 
 * GearAuto uses the Path class to set waypoints on the path to the given peg.
 *
 * Once that path is calculated, it queues the DrivePath command on the generated path, which moves the 
 * robot along the path.
 */
public class GearAuto extends CommandGroup {
	
	 //number of feet the robot needs to go forward from the wall to be able to turn
	final double ROBOT_CLEARANCE_DISTANCE = 1;
	
	final double ROBOT_LENGTH = 3.166;
	
	
	/**
	 * Autonomous command to put the gear in place.
	 * @param peg Index of which peg to target (0-2)
	 * 
	 * Map of peg indexes on airship:
	 * 
	 * 
	 * -                       -
	 *   -                   -
	 *  0  -               -  2
	 *       - - - - - - -
	 *            1
	 * 
	 */
    public GearAuto(int peg) {
    	
    	//throw exception if peg is not one of the expected values
    	if (Math.abs(peg) > 2) {
    		throw new IllegalArgumentException("peg variable must be on domain [0,2]");
    	}
    	
    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	//calculate path relative to saarting position
    	Vector2 currentpos = Robot.drive.position;
    	
    	//create paths
    	Path path0 = new Path();
    	Path path1 = new Path();
    	Path path2 = new Path();
    	
    	
    	//Set Up Paths
    	
    	//due to some quirks with the coordinate system, the conversion from the robot to the field is (y,-x)
    	path0.add(new Vector2(currentpos.X +  ROBOT_CLEARANCE_DISTANCE, currentpos.Y));
    	path0.add(new Vector2(7, -6));
    	path0.add(new Vector2(11.708 - (ROBOT_LENGTH / 2) - 1, -8.885));
    	
    	path1.add(new Vector2(currentpos.X +  ROBOT_CLEARANCE_DISTANCE, currentpos.Y));
    	path1.add(new Vector2(6 - (ROBOT_LENGTH), -13.5));
    	path1.add(new Vector2(9.525 - (ROBOT_LENGTH), -13.5));
    	
    	path2.add(new Vector2(currentpos.X +  ROBOT_CLEARANCE_DISTANCE, currentpos.Y));
    	path2.add(new Vector2(6, -23));
    	path2.add(new Vector2(11.708 - (ROBOT_LENGTH / 2), -18.11));
    	
    	System.out.println(path1);
    	
    	
    	//combine paths into array with indices corresponding to peg index
    	Path[] paths = new Path[] {path0, path1, path2};
    	
    	
    	//queue command to drive the robot along the path
    	addSequential(new DrivePath( paths[peg] ));
    	
    }
}
