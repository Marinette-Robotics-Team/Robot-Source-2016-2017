package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.OI;
import org.usfirst.frc.team6624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReverseShoot extends Command {

	final double AGITATOR_SPEED = -0.4;
	final double SHOOT_SPEED = 1;
	
    public ReverseShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballshooter);
    	requires(Robot.agitator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (OI.xbox.getBButton()) {
    		Robot.agitator.agitaterSpeed(AGITATOR_SPEED);
    		Robot.ballshooter.spinnerSpeed(SHOOT_SPEED);
    	}
    	else {
    		Robot.agitator.agitaterSpeed(0);
    		Robot.ballshooter.spinnerSpeed(0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.agitator.agitaterSpeed(0);
		Robot.ballshooter.spinnerSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		Robot.agitator.agitaterSpeed(0);
		Robot.ballshooter.spinnerSpeed(0);
    }
}
