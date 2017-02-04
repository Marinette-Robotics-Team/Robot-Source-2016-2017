package org.usfirst.frc.team6634.robot.customClasses;

import edu.wpi.first.wpilibj.PIDOutput;


/**
 * This class is used to group together SprakControllers as one PIDOutput
 */
public class PIDOutputGroup implements PIDOutput {

	PIDOutput[] outputs;
	Boolean[] inverted;
	
	/**
	 * Takes PIDoutputs and groups them.
	 * @param outputs Array of PIDOutputs to group
	 * @param inverted Array corresponding to PID outputs, set to true to invert the corresponding PIDOutput
	 */
	public PIDOutputGroup(PIDOutput[] outputs, Boolean[] inverted) {
		if (outputs.length != inverted.length) {
			throw new IllegalArgumentException("There must be a boolean corresponding to every output.");
		}
		
		this.outputs = outputs;
		this.inverted = inverted;
	}
	
	/**
	 * Write pid data to all outputs, inverting those specified by the boolean array
	 */
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		for (int i = 0; i < outputs.length; i++) {
			double out = output;
			if (inverted[i]) {
				out *= 1;
				System.out.println("INVERTED: " + out);
			}
			else {
				System.out.println("NORMAL: " + out);
			}
				
			
			outputs[i].pidWrite(out);
			
		}
	}

}
