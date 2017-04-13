package org.usfirst.frc.team6624.robot.customClasses;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 * This class is used to bind together a list of PIDOutputs that should behave the same 
 * (i.e. two drive motors that should always move together)
 * 
 * This should only be used for putting together PIDOutputs that should react *exactly* the same to a given input
 * 
 * The constructor takes one argument, the list of PIDOutputs to be bound together
 *
 */
public class CompoundPIDOutput implements PIDOutput {

	PIDOutput[] outputs;
	
	/**
	 * Initialize CompoundPIDOutput
	 * @param outputs list of PIDOutputs to bind together
	 */
	public CompoundPIDOutput(PIDOutput[] outputs) {
		this.outputs = outputs;
	}
	
	@Override
	public void pidWrite(double output) {
		for (PIDOutput pidOut : outputs) {
			pidOut.pidWrite(output);
		}
	}

}
