package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.RobotMap;
import org.usfirst.frc.team6624.robot.commands.PrintGyro;
import org.usfurst.frc.team6624.robot.libs.ADXRS453Gyro;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gyroscope extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands
	
	private ADXRS453Gyro gyro;
	
	double localRotation;
	
	public Gyroscope() {
		gyro = new ADXRS453Gyro();
		gyro.startThread();
		
		localRotation = 0;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new PrintGyro());
    }
    
    /**
     * Gets new rotation relative to start
     * @return double representing net rotation in degrees
     */
    public double getGlobalRotation() {
    	return gyro.getAngle();
    }
    
    /**
     * Resets local rotation for external degree measurements
     */
    public void reset() {
    	localRotation = getGlobalRotation();
    }
    
    /**
     * Get local rotation for external degree measurements
     * @return double representing net rotation in degrees
     */
    public double getRotation() {
    	return getGlobalRotation() - localRotation;
    }
    
    
    
}

