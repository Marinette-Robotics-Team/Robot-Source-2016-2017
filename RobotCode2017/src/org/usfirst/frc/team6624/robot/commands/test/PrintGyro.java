package org.usfirst.frc.team6624.robot.commands.test;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *This is a test class for printing out the current gyroscope state.
 *
 *It takes 1 argument, constantPrint
 *
 *If constantPrint is true, the command will constantly output the current gyro reading to the console.
 *
 *Regardless of the value off constantPrint, this class prints the command line warnings that the gyroscope
 *is still calibrating
 *
 */
public class PrintGyro extends Command {
	
	Boolean constantPrint;

	
	/**
	 * Initializes the PrintGyro command, which outputs debug information about the gyroscope
	 * @param constantPrint Boolean that regulates whether the command should constantly output the gyro angle to the console
	 */
    public PrintGyro(Boolean constantPrint) {
    	requires(Robot.gyroscope);
    	
    	this.constantPrint = constantPrint;
    }

    protected void initialize() {
    }

    protected void execute() {
    	//get rotation and print if constantPrint is true, print warnings if gyro is still calibrating
    	double rot = Robot.gyroscope.getGlobalRotation();
    	if (constantPrint) {
    		System.out.println("The rotation is: " + rot);
    	}
    	
    	if (Robot.gyroscope.gyro.isCalibrating()) {
    		System.out.println("WARNING: Gyroscope is still calibrating");
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }


    protected void interrupted() {
    }
}
