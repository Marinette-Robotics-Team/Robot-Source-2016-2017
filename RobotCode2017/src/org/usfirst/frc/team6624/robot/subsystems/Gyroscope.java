package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.commands.test.PrintGyro;
import org.usfirst.frc.team6624.robot.libs.ADXRS453Gyro;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This class acts as an abstraction of the Gyroscope functionality while adding extra functions
 * 
 * This class contains both funtions pertaining to the hardware gyro as well as static functions
 * useful for dealing with angles.
 * 
 * 
 * This class can:
 * 	-get the current angle relative to the starting position
 * 	-get relative angle between 2 states
 * 
 * This class contains static methods to:
 * 	-check if 2 angles are equivalent
 * 	-simplify angles onto [0, 360)
 */
public class Gyroscope extends Subsystem {
	
	public ADXRS453Gyro gyro;
	
	double localRotation;
	
	public Gyroscope() {
		gyro = new ADXRS453Gyro();
		gyro.startThread();
		
		localRotation = 0;
	}
	
    public void initDefaultCommand() {
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
     * Returns boolean if 2 angles are equivalent (degrees or radians)
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
     * Simplify degree angle to be in the range [0, 360) (degrees)
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

