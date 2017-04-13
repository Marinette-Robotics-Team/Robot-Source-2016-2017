package org.usfirst.frc.team6624.robot.commands.ShooterCommands;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;



/**
 * Command for activating the shooter.
 * 
 * Revs the shooter up to RobotMap.SHOOTER_MAX over RobotMap.SHOOTER_REV_UP_TIME,
 * then maintains speed. 
 */
public class Shoot extends Command {
	
	Timer revUpTimer;
		
	
    public Shoot() {
    	requires(Robot.ballshooter);
    	
    	revUpTimer = new Timer(); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
 
    
    // Slowly brings the ball shooter motor up to speed and maintains that speed
    protected void execute() {
    	
    	if (revUpTimer.get() < RobotMap.SHOOTER_REV_UP_TIME) {
    		Robot.ballshooter.setSpinnerSpeed((RobotMap.SHOOTER_MAX / RobotMap.SHOOTER_REV_UP_TIME) * revUpTimer.get());
    	}
    	else {
    		Robot.ballshooter.setSpinnerSpeed(RobotMap.SHOOTER_MAX);
    	} 	
    	
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
		(Robot.ballshooter).setSpinnerSpeed(0);
    }


    protected void interrupted() {
		(Robot.ballshooter).setSpinnerSpeed(0);
    }
}
