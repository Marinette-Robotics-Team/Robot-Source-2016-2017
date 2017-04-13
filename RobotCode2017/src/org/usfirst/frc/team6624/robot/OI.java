package org.usfirst.frc.team6624.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team6624.robot.commands.ShooterCommands.ReverseShoot;
import org.usfirst.frc.team6624.robot.commands.input.CarDrive;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//instantiate xbox and joystick
	public static XboxController xbox = new XboxController(RobotMap.xboxPort);
	
	public static Joystick joystick = new Joystick(RobotMap.joystickPort);
	
	//xbox consts
	public static int xboxLeftX = 0;
	public static int xboxLeftY = 1;
	public static int xboxRightX = 4;
	public static int xboxRightY = 5;

	
	//xbox buttons
	static Button aButton = new JoystickButton(xbox, 1);
	static Button bButton = new JoystickButton(xbox, 2);
	static Button xButton = new JoystickButton(xbox, 3);
	static Button yButton = new JoystickButton(xbox, 4);
	
	//static for external accesibility reasons
	public static Button leftTriggerTop = new JoystickButton(xbox, 5);
	public static Button rightTriggerTop = new JoystickButton(xbox, 6);
	
	static Button backButton = new JoystickButton(xbox, 7);
	static Button startButton = new JoystickButton(xbox, 8);
	
	static Button leftJoystickDown = new JoystickButton(xbox, 9);
	static Button rightJoystickDown = new JoystickButton(xbox, 10);
	
	
	
	//Joystick consts
	public static int joystickX = 0;
	public static int joystickY = 1;
	
	//Joystick buttons (to be setup later)
	Button two = new JoystickButton(joystick, 2);

	
	public OI() {
		
		//toggle input schemes
		xButton.whenPressed(new CarDrive());
		
		// activate agitator 
		//rightTriggerTop.whenPressed(new Agitate());
		
		// activate shooter
		//leftTriggerTop.whenPressed(new Shoot());
		
		
		   
		bButton.whenPressed(new ReverseShoot());
		
		//Active rope climber
		//yButton.whenPressed(new ClimbRope());
		
		//turn off ball shooter and rope climber
		//aButton.whenPressed(new AutonomousTest());
		
		//bButton.whenPressed(new DriveStraightDistance(8, 5, 5, false, 0));
		//bButton.whenPressed(new DriveStraightDistanceBasic(5));
		
	}
	
	
	//Input map
	//maps different commands to button on the xbox controller/joystick
	
	public static final Button AGITATOR_BUTTON = rightTriggerTop;
	public static final Button SHOOTER_BUTTON = leftTriggerTop;
	public static final Button CLIMBER_BUTTON = yButton;
	
}
