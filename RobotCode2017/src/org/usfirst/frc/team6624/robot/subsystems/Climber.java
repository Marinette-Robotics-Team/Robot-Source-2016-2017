package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.RobotMap;
import org.usfirst.frc.team6624.robot.commands.ClimbRope;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Simple subsystem for controlling the climber motor.
 * 
 * This subsystem can:
 * 	-set the speed of the climbing motor
 */
public class Climber extends Subsystem {

	Spark climbMotor = new Spark (RobotMap.climbMotor);

    public void initDefaultCommand() {
    }

    public void  setClimberSpeed(double climbSpeed){
    	climbMotor.set(climbSpeed);
    }
    

}
