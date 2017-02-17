package org.usfirst.frc.team6624.robot.commands;


import org.usfirst.frc.team6624.robot.IO;
import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6634.robot.customClasses.PIDOutputGroup;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveSingleStick extends Command {

	final double MAX_SPEED = 10;
	
	final double JOYSTICK_THRESHOLD = 0.2;
	
	
	protected int xAxis;
	protected int yAxis;
	
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
	
	
	
    public DriveSingleStick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("DriveSingleStick");
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	xAxis = IO.xboxLeftX;
    	yAxis = IO.xboxLeftY;
    	
    	//setupPIDs();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//get axis values (0 - 255)
    	double stickX = IO.xbox.getRawAxis(xAxis);
    	double stickY = IO.xbox.getRawAxis(yAxis);
    	
    	//scale right and left motor power by x axis
    	double rightPower;
    	double leftPower;
    	
    	if (stickX > 0) {
    		rightPower = 1 - 2 * Math.pow(stickX, 5);
    		leftPower = 1;
    	}
    	else if (stickX < 0) {
    		rightPower = 1;
    		leftPower = 1 + 2 * Math.pow(stickX, 5);
    	}
    	else {
    		leftPower = 1;
    		rightPower = 1;
    	}
    	
    	
    	//set motors
    	Robot.drive.setLeftSpeed(-stickY * leftPower, true); //negative accounts for inverse y axis
    	Robot.drive.setRightSpeed(-stickY * rightPower, true);
    	/*
    	if (Math.abs(stickY) >= JOYSTICK_THRESHOLD) {
    		
    		if (!leftEncoderPID.isEnabled() || !leftEncoderPID.isEnabled()) {
	    		leftEncoderPID.enable();
	    		rightEncoderPID.enable();
    		}
    		
	    	leftEncoderPID.setSetpoint(-stickY * leftPower * MAX_SPEED);
	    	rightEncoderPID.setSetpoint(-stickY * rightPower * MAX_SPEED);
    	}
    	else {
    		leftEncoderPID.disable();
    		rightEncoderPID.disable();
    		
	    	leftEncoderPID.setSetpoint(0);
	    	rightEncoderPID.setSetpoint(0);
    	}*/
    	
    	//System.out.println("Left Speed: " + -stickY * leftPower);
    	//System.out.println("Right Speed: " + -stickY * rightPower);
    	
    	Robot.drive.updateTrimInput();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	/*leftEncoderPID.disable();
    	rightEncoderPID.disable();*/
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	/*leftEncoderPID.disable();
    	rightEncoderPID.disable();*/
    }
    
    protected void setupPIDs() {
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
    	
    
    	leftEncoderPID.disable();
    	rightEncoderPID.disable();
    }
}
