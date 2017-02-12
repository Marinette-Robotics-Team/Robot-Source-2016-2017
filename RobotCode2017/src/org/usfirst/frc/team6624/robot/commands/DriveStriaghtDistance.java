package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6634.robot.customClasses.PIDOutputGroup;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *Uses encoder values to drive straight a specified distance
 */
public class DriveStriaghtDistance extends Command {
	
	final double FINAL_ADJUST_SPEED = 0.35;
	final double MIN_SPEED = 0.2;
	
	PIDController leftEncoderPID;
	
	double lEncoderP = 0.01;
	double lEncoderI = 0;
	double lEncoderD = 0;
	
	PIDController rightEncoderPID;
	
	double rEncoderP = 0.01;
	double rEncoderI = 0;
	double rEncoderD = 0;
	
	
	PIDController gyroPID;
	
	double gyroP = 0;
	double gyroI = 0;
	double gyroD = 0;
	
	
	Timer timer;
	
	
	//arguments
	double distance;
	double maxVelocity;
	double acceleration;
	
	
	//speed calculation vars
	
	//amount of time spend on acceleration/deceleration cycle
	double tAccelerating;
	double tCruising;
	double tTotal;
	
	
	//state of the command
	enum DriveState {
		accelerating, 
		cruisung,
		decelerating
	}
	
	DriveState currentState;
	
	/**
	 * Uses encoder values to drive the robot a given distance, using the gyroscope to keep straight
	 * @param distance Distance to travel (ft)
	 * @param maxVelocity Velocity to accelerate to (ft/s)
	 * @param acceleration Acceleration to reach max velocity (ft/s^2)
	 */
    public DriveStriaghtDistance(double distance, double maxVelocity, double acceleration) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    	requires(Robot.gyroscope);
    	
    	if (distance < Math.pow(maxVelocity, 2) / acceleration) {
    		throw new IllegalArgumentException("maxVelocity too high.");
    	}
    	
    	this.distance = distance;
    	this.maxVelocity = maxVelocity;
    	this.acceleration = acceleration;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//instantiate PIDs with motor groups
    	
    	PIDOutputGroup leftSide = new PIDOutputGroup(new PIDOutput[] {Robot.drive.frontLeftMotor, Robot.drive.backLeftMotor}, new Boolean[] {false, false}, 1.0);
    	
    	PIDOutputGroup rightSide = new PIDOutputGroup(new PIDOutput[] {Robot.drive.frontRightMotor, Robot.drive.backRightMotor}, new Boolean[] {true, true}, 1.0);

    	//reset PIDs
    	Robot.drive.leftEncoder.reset();
    	Robot.drive.rightEncoder.reset();
    	
    	//set PID type
    	Robot.drive.leftEncoder.setPIDSourceType(PIDSourceType.kRate);
    	Robot.drive.rightEncoder.setPIDSourceType(PIDSourceType.kRate);
    	
    	leftEncoderPID = new PIDController(lEncoderP, lEncoderI, lEncoderD, Robot.drive.leftEncoder,  leftSide);
    	
    	rightEncoderPID = new PIDController(rEncoderP, rEncoderI, rEncoderD, Robot.drive.rightEncoder, rightSide);
    	
    	leftEncoderPID.setContinuous();
    	rightEncoderPID.setContinuous();
    	
    	
    	leftEncoderPID.enable();
    	rightEncoderPID.enable();
    	
    	
    	//timer things
    	timer = new Timer();
    	timer.start();
    	
    	//set timer times
    	tAccelerating = maxVelocity / acceleration;
    	tCruising = (distance - (Math.pow(maxVelocity, 2) / acceleration)) / maxVelocity;
    	tTotal = 2 * tAccelerating  + tCruising;
    	
    	
    	
    	
    	//set starting state
    	currentState = DriveState.accelerating;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	rightEncoderPID.setSetpoint(getVelocity());
    	leftEncoderPID.setSetpoint(getVelocity());
    	
    	//print out debug data to smartdash
    	SmartDashboard.putNumber("Left Encoder Vel:", Robot.drive.leftEncoder.getRate());
    	SmartDashboard.putNumber("Right Encoder Vel", Robot.drive.rightEncoder.getRate());
    	
    	SmartDashboard.putNumber("Left Encoder Dist", Robot.drive.leftEncoder.getDistance());
    	SmartDashboard.putNumber("Right Encoder Dist", Robot.drive.leftEncoder.getDistance());
    	
    	updateState();
    	
    	//keep at low speed if decelerating period is up
    	if (currentState == DriveState.decelerating && (timer.get() > tAccelerating)) {
        	rightEncoderPID.setSetpoint(FINAL_ADJUST_SPEED);
        	leftEncoderPID.setSetpoint(FINAL_ADJUST_SPEED);
    	}
    }
    
    private void updateState() {
    	if (currentState == DriveState.accelerating) {
    		if (timer.get() >= tAccelerating) {
    			timer.reset();
    			currentState = DriveState.cruisung;
    		}
    	}
    	else if (currentState == DriveState.cruisung) {
    		if (timer.get() >= tCruising) {
    			timer.reset();
    			currentState = DriveState.decelerating;
    		}
    	}
    }

    
    private double getVelocity() {
    	//change output based on current drive state
    	if (currentState == DriveState.accelerating) {
    		return acceleration * timer.get();
    	}
    	else if (currentState == DriveState.cruisung) {
    		return maxVelocity;
    	}
    	else if (currentState == DriveState.decelerating) {
    		return maxVelocity - acceleration * (timer.get());
    	}
    		
    	return 0;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//finish if distance >= requried distance
        return (Robot.drive.leftEncoder.getDistance() >= distance || Robot.drive.rightEncoder.getDistance() >= distance);
    }
    
    
    // Called once after isFinished returns true
    protected void end() {
    	leftEncoderPID.disable();
    	rightEncoderPID.disable();
    	
    	System.out.println("DISTANCE TRAVELED: \nRIGHT:  " + Robot.drive.rightEncoder.getDistance() + "\nLEFT: " + Robot.drive.leftEncoder.getDistance());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
