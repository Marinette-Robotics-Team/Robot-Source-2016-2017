package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.RobotMap;
import org.usfirst.frc.team6624.robot.commands.test.PrintGyro;
import org.usfirst.frc.team6624.robot.libs.ADXRS453Gyro;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gyroscope extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands
	
	public ADXRS453Gyro gyro;
	
	double localRotation;
	
	public Gyroscope() {
		gyro = new ADXRS453Gyro();
		gyro.startThread();
		
		localRotation = 0;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new PrintGyro(false));
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
    
    /**
     * Returns boolean if 2 angles are equivalent (degrees)
     * @param a1 First angle
     * @param a2 Second angle
     * @param percentError Percent error within which angles will be considered 
     * @return Boolean conataining whether the 2 angles are equivalent
     */
    public static Boolean anglesEqual(double a1, double a2, double percentError) {
    	//simplify angles to [0, 360)
    	double ang1 = simplifyAngle(a1);
    	double ang2 = simplifyAngle(a2);
    	
    	if (ang1 == ang2) {
    		return true;
    	}
    	
    	//calculate percent difference and compare to percentError
    	double avg = (ang1 + ang2) / 2;
    	double percentDiff = Math.abs((ang1 - ang2) / avg);
    	return percentDiff <= percentError;
    }
    
    /**
     * Simplify degree angle to be in the range [0, 360)
     * @param angle the angle to be simplified
     * @return the simplified angle
     */
    public static double simplifyAngle(double angle) {
    	double adjustConst = 360;
    	
    	double retAngle = angle;
    	
    	if (retAngle > 0)
    		adjustConst *= -1;
    	
    	//adjust by 360 until within [0, 360)
    	while (retAngle >= 360 || retAngle < 0) {
    		retAngle += adjustConst;
    	}
    	
    	return retAngle;
    }
    
    
}

