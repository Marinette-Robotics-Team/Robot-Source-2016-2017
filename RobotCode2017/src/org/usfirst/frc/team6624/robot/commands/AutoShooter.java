package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoShooter extends Command {

	
	
	final double AGITATOR_MAX = 0.4 * 0.70;
	final double AGITATOR_SPEED_INC = 0.025;
	public static Boolean shooterOnOff = false;
	double agitaterspeed= 0;
	
	boolean agitate = false;
	
	
	
	//final double AGITATOR_MAX = 0.4 * 0.85;
		final double SHOOTER_MAX = -1;
		final double TIME_DELAY = 2;
		
		//public static Boolean shooterOnOff = false;
		
		double shootspeed = 0;
		//double agitaterspeed= 0;
		final double SHOOTSPEEDINC= 0.1;
	
	
	
	
	
	
	
    public AutoShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    requires(Robot.agitator);
    requires(Robot.ballshooter);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    	
    	
       //BallShooter 
          	 
        if (shootspeed > SHOOTER_MAX){
        	shootspeed -= SHOOTSPEEDINC;
        }
        else {
        	shootspeed = SHOOTER_MAX;
        }
        //end BallShooter
        	
    	 //Agitator code
        if(agitate == true){
        	if (agitaterspeed < AGITATOR_MAX){
    		
    		agitaterspeed += AGITATOR_SPEED_INC;
    	}
    	else if (agitaterspeed > AGITATOR_MAX) {
    		agitaterspeed = AGITATOR_MAX;
    		
    		
    	}
    	//end Agitator
    }

    	
    (Robot.ballshooter).spinnerSpeed(shootspeed);
	(Robot.agitator).agitaterSpeed(agitaterspeed);
        
        
    }
	
	
    
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	(Robot.ballshooter).spinnerSpeed(0);
    	(Robot.agitator).agitaterSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
