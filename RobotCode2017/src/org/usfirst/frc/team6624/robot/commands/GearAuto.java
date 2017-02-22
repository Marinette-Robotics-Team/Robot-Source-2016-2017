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
    	
    	//setup is (y,-x)
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
    	
    	//put paths together
    	
    	Path[] paths = new Path[] {path0, path1, path2};
    	

    	addSequential(new DrivePath( paths[peg] ));
    	
    }
}
