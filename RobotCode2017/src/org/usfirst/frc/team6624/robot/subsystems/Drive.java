package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Talon leftFrontMotor = new Talon(RobotMap.leftFrontMotor);
	Talon rightFrontMotor = new Talon(RobotMap.rightFrontMotor);
	Talon leftBackMotor = new Talon(RobotMap.leftBackMotor);
	Talon rightBackMotor = new Talon(RobotMap.rightBackMotor);
	
	RobotDrive drive = new RobotDrive(leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor);
	
	float leftSpeed = 0;
	float rightSpeed = 0;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setLeftSpeed(float speed) {
    	leftSpeed = speed;
    }
    
    public void setRightSpeed(float speed) {
    	rightSpeed = speed;
    }
}

