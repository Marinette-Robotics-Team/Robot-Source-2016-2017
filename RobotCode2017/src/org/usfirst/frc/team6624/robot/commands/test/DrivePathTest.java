package org.usfirst.frc.team6624.robot.commands.test;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.commands.drive.DrivePath;
import org.usfirst.frc.team6624.robot.customClasses.Path;
import org.usfirst.frc.team6624.robot.customClasses.Vector2;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Test for Path system to follow a path.
 *
 */
public class DrivePathTest extends CommandGroup {

    public DrivePathTest() {

    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	Path path = new Path();
    	
    	path.add(new Vector2(0, 5));
    	path.add(new Vector2(0, 8));
    	path.add(new Vector2(5, 8));
    	
    	addSequential(new DrivePath( path ));
    	
    }
}
