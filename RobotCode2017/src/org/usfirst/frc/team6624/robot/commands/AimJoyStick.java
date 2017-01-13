package org.usfirst.frc.team6624.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6624.robot.OI;
import org.usfirst.frc.team6624.robot.Robot;
/**
 *
 */
public class AimJoyStick extends Command {

   

	public AimJoyStick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double joystickX = OI.joystick.getRawAxis(OI.joystickX);
    	double joystickY = OI.joystick.getRawAxis(OI.joystickY);
    	
    	//X axis control
    	if (joystickX < 0.1 && joystickX >-0.1 ){
    		
    		//instert some motor speed code here
    		System.out.println("there be no movement :D");
    		
    	}
    	else if (joystickX > 0.1){
    		
    		//instert some motor speed code here
    		System.out.println("turning right:" + joystickX);
    		
    	}
    	else if (joystickX < -0.1){
    		
    		//insert even more motor speed code here
    		System.out.println("turning left:" + joystickX);
    	}
    	
    	//Y axis control
    	if (joystickY < 0.1 && joystickY >-0.1 ){
    		
    		//instert some motor speed code here
    		System.out.println("there be no movement :D");
    		
    	}
    	else if (joystickY > 0.1){
    		
    		//instert some motor speed code here
    		System.out.println("turning right:" + joystickY);
    		
    	}
    	else if (joystickY < -0.1){
    		
    		//insert even more motor speed code here
    		System.out.println("turning left:" + joystickY);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
