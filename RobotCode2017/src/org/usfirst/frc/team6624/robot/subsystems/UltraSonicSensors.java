package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.RobotMap;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 
 */
public class UltraSonicSensors extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Ultrasonic rangeFinder = new Ultrasonic (RobotMap.rangeFinderPortOut, RobotMap.rangeFinderPortIn);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

