package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;
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
	
	//Vector2s directly in from of corresponding gear pins
	Vector2 lineup0 =  new Vector2(4.66, 2.66);
	Vector2 lineup1 = new Vector2(6.75, 0);
	Vector2 lineup2 = new Vector2(4.66, 4.33);
	
	Vector2[] gearLineupCoords = new Vector2[] {lineup0, lineup1, lineup2};
	
	//angles for final lineup
	double lineupAngle0 = -60;
	double lineupAngle1 = 0;
	double lineupAngle2 = 60;
	
	double[] lineupAngle = new double[] {lineupAngle0, lineupAngle1, lineupAngle2};
	
	
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
    	
    	addSequential(new DriveToCoords(new Vector2(currentpos.X + ROBOT_CLEARANCE_DISTANCE, currentpos.Y)));
    	addSequential(new DriveToCoords(gearLineupCoords[peg]));
    	
    	addSequential(new DriveTurn(lineupAngle[peg]));
    	addSequential(new DriveStraightDistance(APPROACH_DISTANCE, APPROACH_MAX_VELOCITY, APPROACH_ACCELERATION, false, 0));
    	
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
}
