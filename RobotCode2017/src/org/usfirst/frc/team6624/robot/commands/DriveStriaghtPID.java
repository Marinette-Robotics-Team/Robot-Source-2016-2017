package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStriaghtPID extends Command {

	PIDController PID;
	double time;
	
	Timer timer;
	
    public DriveStriaghtPID(double time, double P, double I, double D) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	this.time = time;
    	timer = new Timer();
    	
    	//setup PID with straight driving inversion map
    	PID = new PIDController(P, I, D, Robot.gyroscope.gyro, Robot.drive.driveGroup.setInverted(new Boolean[] {false, false, false, false}));
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	PID.setContinuous();
    	
    	//set goal to current rotation
    	PID.setSetpoint(Robot.gyroscope.gyro.getAngle());
    	
    	
    	PID.enable();
    	
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Boolean finished = timer.get() >= time;
    	
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	PID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	PID.disable();
    }
}
