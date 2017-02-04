package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RoperClimberr extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Spark climbMotor = new Spark (RobotMap.climbMotor);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    
    }

    public void  climb(double ClimbSpeed){
    	climbMotor.set(ClimbSpeed);
    	}
    

}
