package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6634.robot.customClasses.Path;
import org.usfirst.frc.team6634.robot.customClasses.Vector2;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearAuto extends CommandGroup {
	
	 //number of feet the robot needs to go forward from the wall to be able to turn
	final double ROBOT_CLEARANCE_DISTANCE = 3;
	
	//number of feet to go forward to approach gear
	final double APPROACH_DISTANCE = 3;
	
	//approach max velocity and acceleration (make sure v^2/a is less than APPROACH_DISTANCE)
	final double APPROACH_MAX_VELOCITY = 1; // ft/s
	final double APPROACH_ACCELERATION = 1; // ft/s^2
	
	
	
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
    	
    	if (Math.abs(peg) > 2) {
    		throw new IllegalArgumentException("peg variable must be on domain [0,2]");
    	}
    	
    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	Vector2 currentpos = Robot.drive.position;
    	
    	//create paths
    	Path path0 = new Path();
    	Path path1 = new Path();
    	Path path2 = new Path();
    	
    	//setup paths
    	path0.add(new Vector2(4.5, 9));
    	path0.add(new Vector2(9.09, 12.07));
    	
    	path1.add(new Vector2(13.5, 6));
    	path1.add(new Vector2(13.5, 9.53));
    	
    	path2.add(new Vector2(22.5, 9));
    	path2.add(new Vector2(17.83, 12.07));
    	
    	//put paths together
    	
    	Path[] paths = new Path[] {path0, path1, path2};
    	
    	
    	addSequential(new DriveToCoords(new Vector2(currentpos.X + ROBOT_CLEARANCE_DISTANCE, currentpos.Y)));

    	addSequential(new DrivePath( paths[peg] ));
    	
    }
}
