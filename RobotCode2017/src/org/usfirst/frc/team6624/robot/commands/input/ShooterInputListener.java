package org.usfirst.frc.team6624.robot.commands.input;

import org.usfirst.frc.team6624.robot.OI;
import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.commands.ShooterCommands.Shoot;
import org.usfirst.frc.team6624.robot.customClasses.InputListener;

import edu.wpi.first.wpilibj.command.Command;

/**
 *This command listens for  xbox controller input,
 *then activates the ballshooter when it is pressed (toggle scheme)
 *
 *This is a subclass of InputListener which handles all of the button logic.
 *
 *The button it listens for is defined in OI.
 *
 *That is all.
 *
 */
public class ShooterInputListener extends InputListener {

    public ShooterInputListener() {    	
    	super(OI.SHOOTER_BUTTON, InputListener.ListenerType.toggle);
    }

    protected void initialize() {
    	//instantiate command for super
    	command = new Shoot();
    }


    protected void execute() {
    	super.execute();
    }


    protected boolean isFinished() {
        return false;
    }


    protected void end() {
    	super.end();
    }


    protected void interrupted() {
    	super.interrupted();
    }
}
