package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.RobotMap;
import org.usfirst.frc.team6624.robot.commands.daBeeperBooper;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 
 */
public class UltraSonicSensors extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public float sensorSensitiviy = 0.977f;
	
	public AnalogInput rangeFinder = new AnalogInput (RobotMap.rangeFinderPort);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new daBeeperBooper());
    }
}

