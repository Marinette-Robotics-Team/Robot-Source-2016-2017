package org.usfirst.frc.team6624.robot.commands.drive;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.RobotMap;
import org.usfirst.frc.team6624.robot.subsystems.Gyroscope;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This command rotates the robot to the angle given in the argument, 
 * with the initial position at the last gyro calibration given as 0 degrees, 
 * the angle increasing in the counter-clockwise direction.
 * 
 * The command is PID Controlled, with the PID and P, I and D value set in RobotMap.
 * 
 * Values set in RobotMap:
 * DRIVE_TURN_P, ''_I, ''_D - P, I, and D values for PID.
 * ROTATE_SPEED_MAX - maximum speed at which the robot can rotate.
 * ROTATE_ANGLE_TOLERANCE - the number of degrees within which the PID considers that it hit the target.
 * 
 *
 */
public class DriveTurn extends Command {
	
	PIDController PID;

	double degrees;
	
	int rotateDirection;
	
	//enum for rotation direction
	enum RotationDirection {
		CW,
		CCW
	}
	
	RotationDirection direction;
	
	/**
	 * @param degrees dumber of degrees to rotate to the left (negative for right)
	 * @param absoluteRotation toggle of whether to rotate relatively from current position (default) or rotate to gloabl angle
	 */
    public DriveTurn(double degrees) {
    	super("DriveTurn");
    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	this.degrees = Gyroscope.simplifyAngle(degrees);
    	
    	//get rotation direction
    	direction = getRotateDirection();
    	
    	System.out.println("DriveTurn Queued");
    	
    }


    protected void initialize() {
    	
    	//setup PID and set setpoint based on rotation direction
    	if (direction == RotationDirection.CW) {
    		PID = new PIDController(RobotMap.DRIVE_TURN_P, RobotMap.DRIVE_TURN_I, RobotMap.DRIVE_TURN_D, Robot.gyroscope.gyro, Robot.drive.driveTurnCW);
    	}
    	else if (direction == RotationDirection.CCW) {
    		PID = new PIDController(-RobotMap.DRIVE_TURN_P, -RobotMap.DRIVE_TURN_I, -RobotMap.DRIVE_TURN_D, Robot.gyroscope.gyro, Robot.drive.driveTurnCCW);
    	}
    	
    	PID.setContinuous();
    	PID.setSetpoint(degrees);
    	PID.setAbsoluteTolerance(RobotMap.ROTATE_ANGLE_TOLERANCE);
    	
    	PID.enable();
    }

    /**
     *  Returns an enum corresponding to the direction in which the robot should rotate
     *  to reach its target the fastest.
     *  @return enum RotateDircetion, CW for Clockwise, CCW for counter-clockwise
     */
    private RotationDirection getRotateDirection() {
		double current = Gyroscope.simplifyAngle( Robot.gyroscope.getGlobalRotation() );
		
		
		if (Math.max(degrees, current) - Math.min(degrees, current) <= 180) {
			if (Math.min(degrees, current) == current) {
				return RotationDirection.CCW;
			}
			else {
				return RotationDirection.CW;
			}
		}
		else {
			if (Math.min(degrees, current) == current)  {
				return RotationDirection.CW;
			}
			else {
				return RotationDirection.CCW;
			}
			
		}
		
		
		
    }
    
    protected void execute() {
    	SmartDashboard.putNumber("Error", PID.getError());
    	
    	printStatusOutput();
    }

    /**
     * print out the current status of the turning loop (current rotation, and the end goal)
     */
    private void printStatusOutput() {
    	double rotation;
    	
    	rotation = Robot.gyroscope.getGlobalRotation();
    	
    	System.out.println("curr: " + rotation + "\n goal: " + degrees);
    }
    
    
    protected boolean isFinished() {
    	//finish if the PID is where its supposed to be
        return PID.onTarget();
    }

    
    protected void end() {
    	PID.disable();
    	
    	//print debug messages
    	double rotation = Robot.gyroscope.getGlobalRotation();
    	System.out.println("Completed at: " + rotation + "\n With Error: " + (rotation - degrees) );
    }


    
    protected void interrupted() {
    	PID.disable();
    	
    	//print debug messages
    	System.out.println("Rotation done");
    	double rotation = Robot.gyroscope.getGlobalRotation();
    	System.out.println("Interrupted at: " + rotation + "\n With Error: " + (rotation - degrees) );
    }
}
