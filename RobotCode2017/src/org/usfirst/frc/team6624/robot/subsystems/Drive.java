package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.RobotMap;
import org.usfirst.frc.team6624.robot.commands.AimJoyStick;
import org.usfirst.frc.team6624.robot.commands.DriveSingleStick;
import org.usfirst.frc.team6624.robot.commands.DriveTank;
import org.usfirst.frc.team6624.robot.commands.FullForward;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Spark frontLeftMotor = new Spark(RobotMap.frontLeftMotorPort);
	Spark frontRightMotor = new Spark(RobotMap.frontRightMotorPort);
	Spark backLeftMotor = new Spark(RobotMap.backLeftMotorPort);
	Spark backRightMotor = new Spark(RobotMap.backRightMotorPort);
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveTank());
    }
    
    /**
     * Sets speed of left drive motors
     * 
     * @param speed Speed to set, from -1 to 1
     */
    public void setLeftSpeed(double speed) {
    	frontLeftMotor.set(Math.pow(speed, 3));
    	backLeftMotor.set(Math.pow(speed, 3));
    }
    
    /**
     * Sets speed of right drive motors
     * 
     * @param speed Speed to set, from -1 to 1
     */
    public void setRightSpeed(double speed) {
    	frontRightMotor.set(-Math.pow(speed, 3));
    	backRightMotor.set(-Math.pow(speed, 3));
    }
    
}

