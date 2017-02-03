package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.RobotMap;
import org.usfirst.frc.team6624.robot.commands.Shooter;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallShooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Spark ShooterMotor = new Spark (RobotMap.ShooterMotor);
	Spark agitaterMotor = new Spark (RobotMap.agitaterMoter);
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	setDefaultCommand(new Shooter());
    	
    	
    }
    
    public double shootspeed = 0;
    
    public void spinnerSpeed() {	
    	ShooterMotor.set(shootspeed);
    }



public double agitaterspeed = 0;


public void agitaterSpeed() {
	agitaterMotor.set(agitaterspeed);
}
}
