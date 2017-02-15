package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;






/**
 *
 */
public class Agitate extends Command {

	
	final double AGITATOR_MAX = 0.4 * 0.85;
	final double TIME_DELAY = 2;
	
	public static Boolean shooterOnOff = false;
	double agitaterspeed= 0;
	
	Timer timer;
	
	
	
	
    public Agitate() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (agitaterspeed < AGITATOR_MAX && timer.get() >= TIME_DELAY){
    		
    		agitaterspeed += 0.02;
    	}
    	else if (agitaterspeed > AGITATOR_MAX) {
    		agitaterspeed = AGITATOR_MAX;
    	}
    	 else {
    			
    			(Robot.ballshooter).agitaterSpeed(0);
    			(Robot.ballshooter).spinnerSpeed(0);
    			timer.reset();
    			timer.start();
    			
    		}
    	(Robot.ballshooter).agitaterSpeed(agitaterspeed);
    	
	}
	
   
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}