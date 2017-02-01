package org.usfirst.frc.team6624.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightDistence extends Command {

	
	final float DECELERATE_COEFFICIENT = 0.98f;
	final float DEGREE_THRESHOLD = 2; //number of degrees to correct after
	final int ACCEL_SAMPLE_SIZE = 5; //samples for accelerometer to take to determine distence
	
	
	double globalMovement = 0;
	float driveDistence;
	float driveTime;
	float driveSpeed;
	float driveSpeedLeft;
	float driveSpeedRight;
	
	Timer timer;
	
	/**
	 * @param driveTime number of seconds to drive forward for
	 * @param driveSpeed speed to set motors, from -1 to 1
	 */
    public DriveStraightDistence(float driveDistence, float driveSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("DriveForward");
    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	//throw exception if driveSpeed isnt between -1 and 1
    	if (Math.abs((double)driveSpeed) > 1) {
    		throw new IndexOutOfBoundsException("DriveSpeed must be between -1 and 1");
    	}
    	
    	//set instance vars
    	this.driveSpeed = driveSpeed;
    	this.driveDistence = driveDistence;
    	
    	driveSpeedLeft = driveSpeed;
    	driveSpeedRight = driveSpeed;
    	
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	Robot.gyroscope.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentAngle = Robot.gyroscope.getRotation();
    	
    	System.out.println(currentAngle);
    	
    	
    	double x;
    	double y;
    	double xMovement = 0;
    	
    	int count = 0;
    	ArrayList <Number> distenceList = new ArrayList<Number>();
    	ArrayList<Number> xList = new ArrayList <Number> ();
    	ArrayList<Number> gyroY = new ArrayList <Number> ();
    	float distencetraveled = 0;
		while (driveDistence < distencetraveled){
    		
		
    	
    	// Gyro Assisted straight driving
    	if  (currentAngle > DEGREE_THRESHOLD) {
    		driveSpeedLeft = DECELERATE_COEFFICIENT * driveSpeed;
    	}
    	else if (currentAngle <= 0) {
    		driveSpeedLeft = driveSpeed;
    	}
    	
    	if(currentAngle < -DEGREE_THRESHOLD) {
    		driveSpeedRight = DECELERATE_COEFFICIENT * driveSpeed;
    	}
    	else if (currentAngle >= 0){
    		driveSpeedRight = driveSpeed;
    	}
    	
    	Robot.drive.updateTrimInput();
    	Robot.drive.setLeftSpeed(driveSpeedLeft);
    	Robot.drive.setRightSpeed(driveSpeedRight);
    	
    	count = count + 1;
        
    	x = Robot.accel.acc.getX();
        xList.add(x);
        
        
        if (count == ACCEL_SAMPLE_SIZE ){
        	
        	for(Number acceleration : xList){
        	
        	//xMovement = (double) xList.get(0);
        	 
        	distenceList.add( (double)acceleration * (timer.get() / ACCEL_SAMPLE_SIZE));
        	 
        	}//end for
        	
        	timer.reset();
        	
        	for(int i = 0; i < distenceList.size(); i++){
                xMovement += (double) distenceList.get(i);
                }//end for
        	globalMovement += xMovement;
                System.out.println(globalMovement);
                
        }// end if
        
        
       
        
    	
    }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
 
    		
        	
			if (globalMovement >= driveDistence){
				Robot.drive.setLeftSpeed(driveSpeedLeft);
		    	Robot.drive.setRightSpeed(driveSpeedRight);
		    	return true;
			}
			
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
