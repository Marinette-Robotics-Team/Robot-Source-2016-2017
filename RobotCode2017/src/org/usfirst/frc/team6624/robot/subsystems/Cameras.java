package org.usfirst.frc.team6624.robot.subsystems;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Cameras extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	final String URL = "PUT THE URL HERE";
	
	//HttpCamera camera1 = new HttpCamera("Cam1", URL, HttpCamera.HttpCameraKind.kMJPGStreamer);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

