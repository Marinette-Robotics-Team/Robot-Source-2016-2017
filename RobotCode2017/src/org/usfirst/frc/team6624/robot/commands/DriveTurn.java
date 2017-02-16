package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.subsystems.Gyroscope;
import org.usfirst.frc.team6634.robot.customClasses.PIDOutputGroup;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTurn extends Command {
	
	//drive speed for rotation
	final float ROTATE_SPEED = 0.6f;
	
	final double ANGLE_RANGE = 0.3;
	
	double P = 0.08;
	double I = 0;
	double D = 0.16;
	
	PIDController PID;

	double degrees;
	
	int rotateDirection;
	
	Boolean absoluteRotation;
	
	/**
	 * @param degrees dumber of degrees to rotate to the left (negative for right)
	 * @param absoluteRotation toggle of whether to rotate relatively from current position (default) or rotate to gloabl angle
	 */
    public DriveTurn(double degrees, Boolean absoluteRotation) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("DriveTurn");
    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	this.degrees = Gyroscope.simplifyAngle(degrees);
    	this.absoluteRotation = absoluteRotation;
    	
    	System.out.println("DriveTurn Queued");
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyroscope.reset();
    	
    	//calcualte which direction to rotate
    	if (absoluteRotation) {
    		
    		double current = Gyroscope.simplifyAngle( Robot.gyroscope.getGlobalRotation() );
    		
    		
    		if (Math.max(degrees, current) - Math.min(degrees, current) <= 180) {
    			if (Math.min(degrees, current) == current) {
    				rotateDirection = 1;
    			}
    			else {
    				rotateDirection = -1;
    			}
    		}
    		else {
    			if (Math.min(degrees, current) == current)  {
    				rotateDirection = -1;
    			}
    			else {
    				rotateDirection = 1;
    			}
    		}
    	}
    	else {
	    	if (degrees > 0)
	    		rotateDirection = 1;
	    	else
	    		rotateDirection = -1;
	    	
	    	//adjust degrees by starting pos
	    	degrees += Robot.gyroscope.gyro.getAngle();
    	}
    	
    	//setup PID and set setpoint
    	PID = new PIDController(P, I, D, Robot.gyroscope.gyro, Robot.drive.driveGroup.setInverted(new Boolean[] {true, true, true, true}));
    	
    	PID.setContinuous();
    	PID.setSetpoint(degrees);
    	PID.setAbsoluteTolerance(ANGLE_RANGE);
    	
    	PID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Error", PID.getError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	double rotation;
    	
    	//set rotation based on angle settings
    	//if (absoluteRotation)
    		rotation = Robot.gyroscope.getGlobalRotation();
    	//else
    		//rotation = Robot.gyroscope.getRotation();
    	
    	System.out.println("curr: " + rotation + "\n goal: " + degrees);

    	
        return PID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	PID.disable();
    	System.out.println("Rotation done");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	PID.disable();
    	System.out.println("Rotation done");
    }
}
