package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.OI;
import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.RobotMap;
import org.usfirst.frc.team6624.robot.commands.drive.DriveTurn;
import org.usfirst.frc.team6624.robot.commands.input.CarDrive;
import org.usfirst.frc.team6624.robot.commands.input.DriveTank;
import org.usfirst.frc.team6624.robot.customClasses.CompoundPIDOutput;
import org.usfirst.frc.team6624.robot.customClasses.PIDOutputGroup;
import org.usfirst.frc.team6624.robot.customClasses.Vector2;
import org.usfirst.frc.team6624.robot.pathfinding.MapCreator;
import org.xguzm.pathfinding.grid.GridCell;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;



/**
 *This subsystem controls the drivetrain motors.
 *
 *This subsystem can:
 *	-Set left and right drivetrain speeds (-1 to 1, not encoder based)
 *
 *
 *
 */
public class Drive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands
	
	//coordinate system
	//set starting position here
	public Vector2 position = new Vector2(0, 0/*0,-13.5*/);
	
	public GridCell[][] map = MapCreator.createMap();
	
	
	//maotor controllers for drive
	public Spark frontLeftMotor = new Spark(RobotMap.frontLeftMotorPort);
	public Spark frontRightMotor = new Spark(RobotMap.frontRightMotorPort);
	public Spark backLeftMotor = new Spark(RobotMap.backLeftMotorPort);
	public Spark backRightMotor = new Spark(RobotMap.backRightMotorPort);
	
	//make CompoundPIDOutputs for left and right drive train motors
	CompoundPIDOutput leftDriveTrain = new CompoundPIDOutput(new PIDOutput[] {frontLeftMotor, backLeftMotor});
	CompoundPIDOutput rightDriveTrain = new CompoundPIDOutput(new PIDOutput[] {frontRightMotor, backRightMotor});
	
	//PIDOutput for turning counter clockwise and clockwise
	public PIDOutputGroup driveTurnCCW = new PIDOutputGroup(new PIDOutput[] { leftDriveTrain, rightDriveTrain}, 
													new Boolean[] { false, false }, RobotMap.ROTATE_SPEED_MAX);
	
	public PIDOutputGroup driveTurnCW = new PIDOutputGroup(new PIDOutput[] { leftDriveTrain, rightDriveTrain}, 
			new Boolean[] { true, true }, RobotMap.ROTATE_SPEED_MAX);	
	
	//encoders for drivetrain
	public Encoder leftEncoder = new Encoder(RobotMap.leftEncoderChannelA, RobotMap.leftEncoderChannelB, true);
	public Encoder rightEncoder = new Encoder(RobotMap.rightEncoderChannelA, RobotMap.rightEncoderChannelB, false);
	
	
	
	double ratio = 1.17;
	
	
	public Drive() {
		leftEncoder.setDistancePerPulse((1.57 * ratio)/360);
		rightEncoder.setDistancePerPulse((1.57 * ratio)/360);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new CarDrive());
    }
    
    /**
     * Sets speed of left drive motors
     * 
     * @param speed Speed to set, from -1 to 1
     * @param useCurve Whether to apply drive sensitivity curve
     */
    public void setLeftSpeed(double speed, Boolean useCurve) {
    	double sp = speed;
    	
    	if (useCurve) {
    		sp = applyCurve(speed);
    	}
    	
    	frontLeftMotor.set(sp);
    	backLeftMotor.set(sp);
    }
    
    /**
     * Sets speed of right drive motors
     * 
     * @param speed Speed to set, from -1 to 1
     * @param useCurve Whether to apply drive sensitivity curve
     */
    public void setRightSpeed(double speed, Boolean useCurve) {
    	double sp = speed;
    	
    	if (useCurve) {
	    	sp = -applyCurve(speed);
    	}
    	
    	frontRightMotor.set(sp);
    	backRightMotor.set(sp);
    }
    
    public double applyCurve(double speed) {
    	double sp = (1 - RobotMap.TORQUE_RESISTANCE_THRESHOLD) * Math.pow(speed, 3);
    	
    	if (speed > 0) {
    		sp += RobotMap.TORQUE_RESISTANCE_THRESHOLD;
    	}
    	else if (speed < 0) {
    		sp -= RobotMap.TORQUE_RESISTANCE_THRESHOLD;
    	}
    	
    	
    	//apply threshold
    	if (Math.abs(speed) <= RobotMap.DRIVE_JOYSTICK_BUFFER) {
    		return 0;
    	}
    	
    	return sp;
    }
}

