package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.OI;
import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.subsystems.BallShooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;



/**
 *
 */
public class Shooter extends Command {

	
	final double AGITATOR_MAX = 0.4 * 0.85;
	final double SHOOTER_MAX = -1;
	final double TIME_DELAY = 2;
	
	public static Boolean shooterOnOff = false;
	
	double shootspeed = 0;
	double agitaterspeed= 0;
	
	Timer timer;
	
	
	
    public Shooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballshooter);
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }
 
    
    // Slowly brings the ball shooter motor up to speed and maintains that speed
    protected void execute() {
    	
    	Shooter.shooterOnOff = OI.xbox.getBButton();
    	
    	if(Shooter.shooterOnOff){
	 
	    	if (shootspeed > SHOOTER_MAX){
	    		shootspeed -= 0.016;
	    	}
	    	else {
	    		shootspeed = SHOOTER_MAX;
	    	}
	    	(Robot.ballshooter).spinnerSpeed(shootspeed);
	    	
	    	
	    	//brings the agitater up to speed and maintains that speed
	    	if (agitaterspeed < AGITATOR_MAX && timer.get() >= TIME_DELAY){
	    		
	    		agitaterspeed += 0.02;
	    	}
	    	else if (agitaterspeed > AGITATOR_MAX) {
	    		agitaterspeed = AGITATOR_MAX;
	    	}
	    	(Robot.ballshooter).agitaterSpeed(agitaterspeed);
    	}
    	else{
    		
    		(Robot.ballshooter).agitaterSpeed(0);
    		(Robot.ballshooter).spinnerSpeed(0);
    		timer.reset();
    		timer.start();
    		
    	}
    	
    	
    	 
    	
    	
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		(Robot.ballshooter).agitaterSpeed(0);
		(Robot.ballshooter).spinnerSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		(Robot.ballshooter).agitaterSpeed(0);
		(Robot.ballshooter).spinnerSpeed(0);
    }
}
