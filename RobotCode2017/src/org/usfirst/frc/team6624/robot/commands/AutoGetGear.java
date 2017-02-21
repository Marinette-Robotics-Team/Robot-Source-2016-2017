package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoGetGear extends Command {

	
	Timer timer;

	
	
    public AutoGetGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    	timer.reset();
    	timer.start();
    	Robot.drive.setLeftSpeed(0, false);
    	Robot.drive.setRightSpeed(0, false);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if (timer.get() > 1){
			System.out.println("Deliver Gear");
			
		}else
		{
			
			System.out.println("Waiting for gear");
		}
	}
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
