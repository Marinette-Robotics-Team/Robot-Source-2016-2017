package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem controls the ball shooter.
 * 
 * This subsystem can:
 * 	-Set the speed of the drive wheel for the shooter
 *
 */
public class BallShooter extends Subsystem {

	
	Spark shooterMotor = new Spark (RobotMap.ShooterMotor);
	

    public void initDefaultCommand() {
    }
    
    
    public void setSpinnerSpeed( double shootspeed) {	
    	shooterMotor.set(shootspeed);
    }

}