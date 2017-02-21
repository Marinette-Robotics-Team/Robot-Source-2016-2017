package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6634.robot.customClasses.Vector2;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
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
