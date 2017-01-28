package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.OI;
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
	
	//trim vals
	double trim = 1;
	
	Boolean isDpadPressed = false;
	int prevDpad = -1;
	

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
    	double sp = Math.pow(speed, 3);
    	
    	//apply trim
    	if (this.trim < 1) {
    		sp *= trim;
    	}
    	
    	frontLeftMotor.set(sp);
    	backLeftMotor.set(sp);
    }
    
    /**
     * Sets speed of right drive motors
     * 
     * @param speed Speed to set, from -1 to 1
     */
    public void setRightSpeed(double speed) {
    	double sp = -Math.pow(speed, 3);
    	
    	//apply trim
    	if (this.trim > 1) {
        	sp *= 2 - trim;
    	}
    	
    	frontRightMotor.set(sp);
    	backRightMotor.set(sp);
    }
    
    /**
     * Set trim for drive
     * 1 : even voltage
     * 0 - 1 : tend left
     * 1 -2 : tend right
     * 
     * @param trim set trim value
     */
    public void setTrim(double trim) {
    	this.trim = trim;
    }
    
    /**
     * Increment or decrement trim
     * @param inc amount to increment
     */
    public void incrementTrim(double inc) {
    	this.trim += inc;
    }
    
    /**
     * function to be called by every manual control command
     * updates trim based on d-pad
     */
    public void updateTrimInput() {
    	int dPad = OI.xbox.getPOV(0);
    	
    	if (dPad != -1) {
    		isDpadPressed = true;
    		prevDpad = dPad;
    	}
    	else if (isDpadPressed) {
    		isDpadPressed = false;
    		
        	if (prevDpad == 90) {//right pressed (tend left)
        		incrementTrim(-0.01);
        	}
        	else if (prevDpad == 270) { //left pressed (tend right)
        		incrementTrim(0.01);
        	}
    	}
    	
    	//System.out.println(trim);
    }
}

