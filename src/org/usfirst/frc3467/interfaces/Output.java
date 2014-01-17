package org.usfirst.frc3467.interfaces;

import edu.wpi.first.wpilibj.PIDOutput;

public class Output implements PIDOutput {
	
	private double output = 0.0;
	
	public void pidWrite(double output) {
		this.output = output;
	}
	
	public double getOutput() {
		return output;
	}
	
}
