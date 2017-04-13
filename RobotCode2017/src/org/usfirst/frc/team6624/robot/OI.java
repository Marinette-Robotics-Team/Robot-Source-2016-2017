package org.usfirst.frc.team6624.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team6624.robot.commands.ShooterCommands.ReverseShoot;
import org.usfirst.frc.team6624.robot.commands.drive.DriveTurn;
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
	
	//Joystick buttons
	public static Button joystickOne = new JoystickButton(joystick, 1);
	public static Button joystickTwo = new JoystickButton(joystick, 2);
	public static Button joystickThree = new JoystickButton(joystick, 3);
	public static Button joystickFour = new JoystickButton(joystick, 4);
	public static Button joystickFive = new JoystickButton(joystick, 5);
	public static Button joystickSix = new JoystickButton(joystick, 6);
	public static Button joystickSeven = new JoystickButton(joystick, 7);
	public static Button joystickEight = new JoystickButton(joystick, 8);
	public static Button joystickNine = new JoystickButton(joystick, 9);
	public static Button joystickTen = new JoystickButton(joystick, 10);

	
	public OI() {
	
		xButton.whenPressed(new CarDrive());
		
		bButton.whenPressed(new ReverseShoot());
		
		//rotate to peg orientations when buttons pressed
		joystickOne.whenPressed(new DriveTurn(-30)); //peg 0          \              /
		joystickTwo.whenPressed(new DriveTurn(0)); //peg 1           0 \            / 2
		joystickThree.whenPressed(new DriveTurn(30)); //peg 2           \----------/  
		                                              //                      1 
		
		
	}
	
	
	//Input map
	//maps different commands to button on the xbox controller/joystick
	
	public static final Button AGITATOR_BUTTON = rightTriggerTop;
	public static final Button SHOOTER_BUTTON = leftTriggerTop;
	public static final Button CLIMBER_BUTTON = yButton;
	
}
