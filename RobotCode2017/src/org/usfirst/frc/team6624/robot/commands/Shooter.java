package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.subsystems.BallShooter;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 */
public class Shooter extends Command {

	double shootspeed = 0;
	double agitaterspeed= 0;
	
	
	
	
	
    public Shooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballshooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    	
		if( Robot.shooterOnOff == 0){
    		
    		Robot.shooterOnOff = 1;
    		
    	}
    	else{
    		
    		Robot.shooterOnOff = 0;
    		
    	}
    }
 
    
    // Slowly brings the ball shooter motor up to speed and maintains that speed
    protected void execute() {
    	
    	if(Robot.shooterOnOff != 1){
 
    	if (shootspeed > -0.8){
    		shootspeed -= 0.002;
    	}
    	(Robot.ballshooter).spinnerSpeed(shootspeed);
    	
    	
    	//brings the agitater up to speed and maintains that speed
    	if (agitaterspeed < 0.4 ){
    		
    		agitaterspeed += 0.01;
    	}
    	(Robot.ballshooter).agitaterSpeed(agitaterspeed);
    	}
    	else{
    		
    		(Robot.ballshooter).agitaterSpeed(0);
    		(Robot.ballshooter).spinnerSpeed(0);
    		
    	}
    	
    	 
    	
    	
    	
    	
    	
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
