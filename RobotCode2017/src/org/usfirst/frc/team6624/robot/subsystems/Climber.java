package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.RobotMap;
import org.usfirst.frc.team6624.robot.commands.ClimbRope;
import org.usfirst.frc.team6624.robot.commands.input.ClimberInputListener;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Spark climbMotor = new Spark (RobotMap.climbMotor);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClimberInputListener());
    }

    public void  setClimberSpeed(double climbSpeed){
    	climbMotor.set(climbSpeed);
    }
    

}
