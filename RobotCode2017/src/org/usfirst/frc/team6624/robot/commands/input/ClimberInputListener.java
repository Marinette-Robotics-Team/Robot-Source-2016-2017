package org.usfirst.frc.team6624.robot.commands.input;

import org.usfirst.frc.team6624.robot.OI;
import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.commands.ClimbRope;
import org.usfirst.frc.team6624.robot.customClasses.InputListener;

/**
 *This command listens for  xbox controller input,
 *then activates the climber while it is pressed (trigger scheme)
 *
 *This is a subclass of InputListener which handles all of the button logic.
 *
 *The button it listens for is defined in OI.
 *
 *That is all.
 *
 */
public class ClimberInputListener extends InputListener {

    public ClimberInputListener() {
    	
    	super(OI.CLIMBER_BUTTON, InputListener.ListenerType.trigger); //activate ONLY when button is pressed
    	
    	requires(Robot.climber);
    	
    }

    protected void initialize() {
    	//instantiate command for super
    	command = new ClimbRope();
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
