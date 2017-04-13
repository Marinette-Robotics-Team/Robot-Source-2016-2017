package org.usfirst.frc.team6624.robot.commands;

import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command for activating the climber.
 * 
 * Motor speed is set by RobotMap.CLIMBER_SPEED.
 * 
 * The motor runs until the command is ended.
 * 
 * 
 */
public class ClimbRope extends Command {

    public ClimbRope() {
    	requires(Robot.climber);
    }


    protected void initialize() {
    }


    protected void execute() {
    	Robot.climber.setClimberSpeed(RobotMap.CLIMBER_SPEED);
    }

    
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.climber.setClimberSpeed(0);
    }


    protected void interrupted() {
    	Robot.climber.setClimberSpeed(0);
    }
}
