package org.usfirst.frc.team6624.robot.commands.ShooterCommands;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This is a Test class to output the current accelerometer data.
 * 
 * It takes no arguments, and prints out to the console constantly.
 */
public class PrintAccelerometer extends Command {

    public PrintAccelerometer() {
    	super("PrintAccelerometer");
    	requires(Robot.accel);
    	
    }

    
    protected void initialize() {
    }

    
    protected void execute() {
    	double x = Robot.accel.acc.getX();
    	double y = Robot.accel.acc.getY();
    	double z = Robot.accel.acc.getZ();
    	
    	System.out.println("Accelerometer X:" + x + " Y:" + y + " Z:" + z);
    }


    protected boolean isFinished() {
        return false;
    }


    protected void end() {
    }


    protected void interrupted() {
    }
}
