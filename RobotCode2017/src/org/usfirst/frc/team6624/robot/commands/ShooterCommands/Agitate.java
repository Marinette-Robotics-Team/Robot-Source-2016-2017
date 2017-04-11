package org.usfirst.frc.team6624.robot.commands.ShooterCommands;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


/**
 * 
 *This command controls the ball agitator.
 *
 *It takes no arguments, as all of the values have been pre-calibrated and can be set in RobotMap
 *
 *The constants used are AGITATOR_MAX, which sets the max speed for the agitator
 *REV_UP_TIME is the number of seconds to rev up to full speed
 *
 */
public class Agitate extends Command {
	

	
	//Whats better than this line?
	
	Timer revUpTimer;
	
	
	
    public Agitate() {
    	requires(Robot.agitator);
    	
    	revUpTimer = new Timer();
    }

    //start timer
    protected void initialize() {
    	revUpTimer.reset();
    	revUpTimer.start();
    }

    protected void execute() {
    	
    	//set current agitator speed based on timer (for revving up)
    	if (revUpTimer.get() < RobotMap.AGITATOR_REV_UP_TIME) {
    		Robot.agitator.setAgitatorSpeed((RobotMap.AGITATOR_MAX / RobotMap.AGITATOR_REV_UP_TIME) * revUpTimer.get());
    	}
    	else {
    		Robot.agitator.setAgitatorSpeed(RobotMap.AGITATOR_MAX);
    	}
    	
	}
	
   
    
    protected boolean isFinished() {
        return false;
    }

    //turn off agitator
    protected void end() {
    	Robot.agitator.setAgitatorSpeed(0);
    }

    //turn off agitator if interrupted
    protected void interrupted() {
    	Robot.agitator.setAgitatorSpeed(0);
    }
}
