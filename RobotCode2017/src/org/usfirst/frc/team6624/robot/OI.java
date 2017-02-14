package org.usfirst.frc.team6624.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team6624.robot.commands.CalibrateDriveTurn;
import org.usfirst.frc.team6624.robot.commands.ClimbRope;
import org.usfirst.frc.team6624.robot.commands.DriveDualPowerTurn;
import org.usfirst.frc.team6624.robot.commands.DriveSingleStick;
import org.usfirst.frc.team6624.robot.commands.DriveStraight;
import org.usfirst.frc.team6624.robot.commands.DriveStraightDistanceBasic;
import org.usfirst.frc.team6624.robot.commands.DriveStriaghtDistance;
//import org.usfirst.frc.team6624.robot.commands.DriveStraightDistence;
import org.usfirst.frc.team6624.robot.commands.DriveTank;
import org.usfirst.frc.team6624.robot.commands.DriveToCoords;
import org.usfirst.frc.team6624.robot.commands.DriveTurn;
import org.usfirst.frc.team6624.robot.commands.DriveTurnApprox;
import org.usfirst.frc.team6624.robot.commands.ExampleCommand;
import org.usfirst.frc.team6624.robot.commands.GetRotConst;
import org.usfirst.frc.team6624.robot.commands.Shooter;
import org.usfirst.frc.team6624.robot.commands.ShooterClimberOff;
import org.usfirst.frc.team6624.robot.commands.daBeeperBooper;
import org.usfirst.frc.team6634.robot.customClasses.Vector2;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	
	public static XboxController xbox = new XboxController(RobotMap.xboxPort);
	
	public static Joystick joystick = new Joystick(RobotMap.joystickPort);
	
	//xbox consts
	public static int xboxLeftX = 0;
	public static int xboxLeftY = 1;
	public static int xboxRightX = 4;
	public static int xboxRightY = 5;
	
	//xbox buttons
	Button aButton = new JoystickButton(xbox, 1);
	Button bButton = new JoystickButton(xbox, 2);
	Button xButton = new JoystickButton(xbox, 3);
	Button yButton = new JoystickButton(xbox, 4);
	
	
	Button leftTriggerTop = new JoystickButton(xbox, 5);
	Button rightTriggerTop = new JoystickButton(xbox, 6);
	
	//Joystick consts
	public static int joystickX = 0;
	public static int joystickY = 1;
	
	//Joystick buttons (to be setup later)
	Button two = new JoystickButton(joystick, 2);

	
	public OI() {
		
		//toggle input schemes
		aButton.whenPressed(new DriveTank());
		xButton.whenPressed(new DriveDualPowerTurn());
		
		
		rightTriggerTop.whenPressed(/*new DriveStriaghtDistance(8, 5, 5)*/ new DriveTurn(180, false));
		leftTriggerTop.whenPressed(new DriveTurn(90, true/*, 0.08, 0, 0.16*/));
		
		//get ultrasonic
		two.whenPressed(new daBeeperBooper());
		
		
		//agitater spin modes   
		
		
	     //int rotationMode = 0;
		//bButton.whenPressed(new Shooter());
		
		
		
		//do a climb
		//yButton.whenPressed(new ClimbRope());
		
		//dont do a climb and dont do a spin ball shoot
		aButton.whenPressed(new ShooterClimberOff());
		
	}
	
	
	
}
