package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.commands.ShooterCommands.PrintAccelerometer;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 * This class is just a subsystem container for the onboard accelerometer
 */
public class Accel extends Subsystem {
	
	//access this variable to access accelerometer data
	public Accelerometer acc;
	
	public Accel() {
		acc = new BuiltInAccelerometer();
	}

    public void initDefaultCommand() {
    	//setDefaultCommand(new PrintAccelerometer());
    }
}

