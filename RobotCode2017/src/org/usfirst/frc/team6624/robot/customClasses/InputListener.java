package org.usfirst.frc.team6624.robot.customClasses;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.RobotMap;
import org.usfirst.frc.team6624.robot.commands.ShooterCommands.Agitate;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * This command is used to listen for input from a controller or joytick button, and execute a command
 * based on that input.
 * 	- This command is to be used rather than button command setup in IO because:
 * 		-Using a command makes it easier to set when the button should listen for commands and when it should not
 * 		-This command has more ListenerTypes than are possible from IO (e.g toggle)
 * 
 * Actual listeners are commands that inherit from this class
 * 
 * Command that extend this class must:
 * 	-Call the super() constructor with the button to listen on and the ListenerType
 * 	-instantiate the Command command with the proper command type in initialize
 * 
 * 
 * InputListener responds to button input differently based on the set ListenerType
 * There are 3 types:
 * 	-execute - the simplest type, run the command when the button is pressed, run the command until it's finished
 * 	-trigger - run the command when the button is held down, cancel the command when the button is released
 * 	-toggle - execute the command when the button is pressed, stop the command when the button is pressed again
 * 			-acts as a toggle
 * 
 * @author Nic Hodlofski
 *
 */
public abstract class InputListener extends Command {
	
	public static enum ListenerType {
		toggle,
		trigger,
		execute
	}
	
	//variable to hold command
	protected Command command;
	
	//store current state of input (true - pressed / false - released)
	boolean buttonState = false;
	
	//store type for this instance
	ListenerType type;
	
	//button to listen for
	Button listenButton;
	
	/**
	 * Instantiate the InputListener, setup with button to listen for and how to respond to input
	 * @param listenButton Which button to listen for
	 * @param type enum ListenerType, defines what input to listen for that should tirgger the command
	 */
    public InputListener(Button listenButton, ListenerType type) {
    	this.listenButton = listenButton;
    	this.type = type;
    }


	protected void initialize() {
    	//command **MUST** be instantiated here in child class
    }

    // listen for button input
    protected void execute() {
    	
    	//listen for input and act accordingly, based on listenType
    	if (type == ListenerType.toggle) {
    		toggleUpdate();
    	}
    	else if (type == ListenerType.trigger) {
    		triggerUpdate();
    	}
    	else if (type == ListenerType.execute) {
    		executeUpdate();
    	}

    }

    //if the button is pressed, run the command and stop listening until it's done
    private void executeUpdate() {
    	if (listenButton.get() && !buttonState) {
    		
    		if (!command.isRunning()) {
    			command.start();
    		}
    		
    		buttonState = true;
    	}
    	//reset button state when no input
    	else {
    		buttonState = false;
    	}		
	}

	//activate command only if button held down
    private void triggerUpdate() {
    	if (listenButton.get() && !buttonState) {
    		command.start();
    		
    		buttonState = true;
    	}
    	//cancel command when no input
    	else {
    		command.cancel();
    		
    		buttonState = false;
    	}
	}

    //toggle command based on button input
	private void toggleUpdate() {
		
    	if (listenButton.get() && !buttonState) {
    		
    		//start the command if it isn't running, cancel it if it is
    		if (command.isCanceled() || !command.isRunning()) {
    			command.start();
    		}
    		else {
    			command.cancel();
    		}
    		
    		buttonState = true;
    	}
    	//reset button state when no input
    	else {
    		buttonState = false;
    	}
		
	}

	protected boolean isFinished() {
        return false;
    }

    //kill command when ended
    protected void end() {
    	command.cancel();
    }

    
    //kill command when interrupted
    protected void interrupted() {
    	command.cancel();
    }
}
