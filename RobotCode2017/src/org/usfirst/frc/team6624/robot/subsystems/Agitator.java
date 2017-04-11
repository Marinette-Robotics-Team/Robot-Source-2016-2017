package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.RobotMap;
import org.usfirst.frc.team6624.robot.commands.input.AgitatorInputListener;
import org.usfirst.frc.team6624.robot.customClasses.InputListener;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem controls the ball agitator
 * 
 * This subsystem can:
 * 	-Set the speed of the agitator.
 * 
 */
public class Agitator extends Subsystem {
	
	Spark agitatorMotor = new Spark (RobotMap.agitatorMotor);

    public void initDefaultCommand() {
    	//listen for control input by default
    	setDefaultCommand(new AgitatorInputListener());
    }
    
    public void setAgitatorSpeed(double agitatorspeed) {
    	agitatorMotor.set(agitatorspeed);
    }
}

