package org.usfirst.frc.team6624.robot;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////INPUT PORTS/////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//setup control ports
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
	
	//DIO ports
	public static int rightEncoderChannelA = 0;
	public static int rightEncoderChannelB = 1;
	public static int leftEncoderChannelA = 2;
	public static int leftEncoderChannelB = 3;
	
	/////////////////////////Input map/////////////////////////////////////////////////
	//maps different commands to button on the xbox controller/joystick
	
	public static final Button AGITATOR_BUTTON = OI.rightTriggerTop;
	
	
	//////////////////////Command Constants///////////////////////////////////////////////
	//it made sense to put certain command constants here, as they never needed to change
	//and centralizing them makes them easy to modify on the fly
	
	//agitator constants
	public static final double AGITATOR_MAX = 1;
	public static final double AGITATOR_REV_UP_TIME = 0.4;
	
	//shooter constants
	public static final double SHOOTER_MAX = 1;
	public static final double SHOOTER_REV_UP_TIME = 2;
	
	//climber constant
	public static final double CLIMBER_SPEED = 1;
	
	//drive rotation constants
	public static final double ROTATE_SPEED_MAX = 0.3;
	public static final double ROTATE_ANGLE_TOLERANCE = 1.5;
	
	
	
	////////////////////////////////////////////PIDS/////////////////////////////////////
	//PID constants and loops are defined here for code clarity
	
	//Drive Turn PID
	
	public static double DRIVE_TURN_P = 0.08;
	public static double DRIVE_TURN_I = 0;
	public static double DRIVE_TURN_D = 0.16;
	
	
	
}
