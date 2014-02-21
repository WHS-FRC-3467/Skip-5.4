package org.usfirst.frc3467.subsystems.rollers.custom;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class CustomPot extends AnalogPotentiometer {
	private double offset = 0;
	
	public CustomPot(int channel, double scale) {
		super(channel, scale);
	}
	
	public double pidGet() {
		// System.out.println(offset + "         " + (this.get() + offset) / (22.0 / 12.0));
		// System.out.println(this.get());
		return (this.get() + offset);
	}
	
	public void setOffset(double offset) {
		this.offset = offset;
	}
	
}
