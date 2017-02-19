package org.usfirst.frc.team6624.robot.subsystems;

import org.usfirst.frc.team6624.robot.IO;
import org.usfirst.frc.team6624.robot.Robot;
import org.usfirst.frc.team6624.robot.RobotMap;
import org.usfirst.frc.team6624.robot.commands.AimJoyStick;
import org.usfirst.frc.team6624.robot.commands.DriveDualPowerTurn;
import org.usfirst.frc.team6624.robot.commands.DriveSingleStick;
import org.usfirst.frc.team6624.robot.commands.DriveTank;
import org.usfirst.frc.team6624.robot.commands.DriveTurn;
import org.usfirst.frc.team6624.robot.commands.FullForward;
import org.usfirst.frc.team6634.robot.customClasses.MapCreator;
import org.usfirst.frc.team6634.robot.customClasses.PIDOutputGroup;
import org.usfirst.frc.team6634.robot.customClasses.Vector2;
import org.xguzm.pathfinding.grid.GridCell;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands
	
	//coordinate system
	//set starting position here
	public Vector2 position = new Vector2(0, 13.5);
	
	public GridCell[][] map = MapCreator.createMap();
	
	
	//maotor controllers for drive
	public Spark frontLeftMotor = new Spark(RobotMap.frontLeftMotorPort);
	public Spark frontRightMotor = new Spark(RobotMap.frontRightMotorPort);
	public Spark backLeftMotor = new Spark(RobotMap.backLeftMotorPort);
	public Spark backRightMotor = new Spark(RobotMap.backRightMotorPort);
	
	//PIDOutput for drive
	public PIDOutputGroup driveGroup = new PIDOutputGroup(new PIDOutput[] { frontLeftMotor, backLeftMotor,
													frontRightMotor, backRightMotor}, 
													new Boolean[] { false, false, false, false}, DriveTurn.ROTATE_SPEED);
	
	//encoders for drivetrain
	public Encoder leftEncoder = new Encoder(RobotMap.leftEncoderChannelA, RobotMap.leftEncoderChannelB, true);
	public Encoder rightEncoder = new Encoder(RobotMap.rightEncoderChannelA, RobotMap.rightEncoderChannelB, false);
	
	//trim vals
	double trim = 1;
	
	Boolean isDpadPressed = false;
	int prevDpad = -1;
	
	
	public Drive() {
		leftEncoder.setDistancePerPulse(1.56/360);
		rightEncoder.setDistancePerPulse(1.56/360);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveDualPowerTurn());
    }
    
    /**
     * Sets speed of left drive motors
     * 
     * @param speed Speed to set, from -1 to 1
     * @param useCurve Whether to apply drive sensitivity curve
     */
    public void setLeftSpeed(double speed, Boolean useCurve) {
    	double sp = speed;
    	
    	if (useCurve) {
	    	sp = Math.pow(speed, 3);
	    	
	    	//apply trim
	    	if (this.trim < 1) {
	    		sp *= trim;
	    	}
    	}
    	
    	frontLeftMotor.set(sp);
    	backLeftMotor.set(sp);
    }
    
    /**
     * Sets speed of right drive motors
     * 
     * @param speed Speed to set, from -1 to 1
     * @param useCurve Whether to apply drive sensitivity curve
     */
    public void setRightSpeed(double speed, Boolean useCurve) {
    	double sp = speed;
    	
    	if (useCurve) {
	    	sp = -Math.pow(speed, 3);
	    	
	    	//apply trim
	    	if (this.trim > 1) {
	        	sp *= 2 - trim;
	    	}
    	}
    	
    	frontRightMotor.set(sp);
    	backRightMotor.set(sp);
    }
    
    /**
     * Set trim for drive
     * 1 : even voltage
     * 0 - 1 : tend left
     * 1 -2 : tend right
     * 
     * @param trim set trim value
     */
    public void setTrim(double trim) {
    	this.trim = trim;
    }
    
    /**
     * Increment or decrement trim
     * @param inc amount to increment
     */
    public void incrementTrim(double inc) {
    	this.trim += inc;
    }
    
    /**
     * function to be called by every manual control command
     * updates trim based on d-pad
     */
    public void updateTrimInput() {
    	int dPad = IO.xbox.getPOV(0);
    	
    	if (dPad != -1) {
    		isDpadPressed = true;
    		prevDpad = dPad;
    	}
    	else if (isDpadPressed) {
    		isDpadPressed = false;
    		
        	if (prevDpad == 90) {//right pressed (tend left)
        		incrementTrim(-0.01);
        	}
        	else if (prevDpad == 270) { //left pressed (tend right)
        		incrementTrim(0.01);
        	}
    	}
    	
    	//System.out.println(trim);
    }
}

