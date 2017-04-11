package org.usfirst.frc.team6624.robot;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static int xboxPort = 0;
	public static int joystickPort = 1;
	
	//PWM Ports
	//drive speedcontrollers
	// Motor Port Map
	public static int frontLeftMotorPort = 1;
	public static int frontRightMotorPort = 3;
	public static int backLeftMotorPort = 0;
	public static int backRightMotorPort = 2;
	public static int ShooterMotor =4;
	public static int agitatorMotor = 5;
	public static int climbMotor = 6;
	
	
	//Analog ports
	public static int gyroPort = 1;
	public static int rangeFinderPort = 0;
	
	//DIO ports
	public static int rightEncoderChannelA = 0;
	public static int rightEncoderChannelB = 1;
	public static int leftEncoderChannelA = 2;
	public static int leftEncoderChannelB = 3;
	
	//Input map
	//maps different commands to button on the xbox controller/joystick
	
	public static final Button AGITATOR_BUTTON = OI.rightTriggerTop;
	
	
	//Command Constants
	//it made sense to put certain command constants here, as they never needed to change
	//and centralizing them makes them easy to modify on the fly
	
	//agitator constants
	public static final double AGITATOR_MAX = 1;
	public static final double AGITATOR_REV_UP_TIME = 0.4;
	
	//
	
	
}
