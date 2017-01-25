package org.usfirst.frc.team6624.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class daBeeperBooper extends Command {

	int counter;
	
	ArrayList<Number> results;
	
	public daBeeperBooper() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.USS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	counter = 0;
    	
    	results = new ArrayList<Number>();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//finds distance to object from the ultrasound sensor
    	
	    double result =	 (Robot.USS.rangeFinder.getVoltage() / Robot.USS.sensorSensitiviy);
	    
	    results.add(result);
	    	
	    //System.out.println("Something is " + result + "m " + "away.");
	    	
	    SmartDashboard.putNumber("Sensor Distance", result);
	    
	    float avg = 0;
	    
	    for (Number num : results) {
	    	avg += num.floatValue();
	    }
	    
	    avg /= results.size();
	    
	    SmartDashboard.putNumber("Avg Dist", avg);
    	
    	counter++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
