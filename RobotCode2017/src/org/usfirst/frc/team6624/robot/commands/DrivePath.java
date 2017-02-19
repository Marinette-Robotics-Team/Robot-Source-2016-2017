package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6634.robot.customClasses.Path;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DrivePath extends CommandGroup {

	
    public DrivePath(Path path) {
    	
    	for (int i = 0; i < path.size(); i++) {
    		addSequential(new DriveToCoords( path.getNext() ));
    	}
    	
    }
}
