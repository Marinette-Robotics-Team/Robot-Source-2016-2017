package org.usfirst.frc.team6624.robot.commands.drive;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.RobotMap;
import org.usfirst.frc.team6624.robot.customClasses.PIDOutputGroup;
import org.usfirst.frc.team6624.robot.subsystems.Gyroscope;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
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
	
	/**
	 * @param degrees dumber of degrees to rotate to the left (negative for right)
	 * @param absoluteRotation toggle of whether to rotate relatively from current position (default) or rotate to gloabl angle
	 */
    public DriveTurn(double degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("DriveTurn");
    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	this.degrees = Gyroscope.simplifyAngle(degrees);
    	
    	System.out.println("DriveTurn Queued");
    	
    }


    protected void initialize() {
    	
    	//setup PID and set setpoint
    	PID = new PIDController(RobotMap.DRIVE_TURN_P, RobotMap.DRIVE_TURN_I, RobotMap.DRIVE_TURN_D, Robot.gyroscope.gyro, Robot.drive.driveGroup.setInverted(new Boolean[] {true, true, true, true}));
    	
    	PID.setContinuous();
    	PID.setSetpoint(degrees);
    	PID.setAbsoluteTolerance(RobotMap.ROTATE_ANGLE_TOLERANCE);
    	
    	PID.enable();
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
