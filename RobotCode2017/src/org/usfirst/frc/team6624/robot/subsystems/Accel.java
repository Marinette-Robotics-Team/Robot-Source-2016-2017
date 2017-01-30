package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.commands.PrintAccelerometer;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 * This class is just a subsystem container for the onboard accelerometer to make sure that 2 commands aren't using it at once
 */
public class Accel extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Accelerometer acc;
	
	public Accel() {
		acc = new BuiltInAccelerometer();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new PrintAccelerometer());
    }
}

