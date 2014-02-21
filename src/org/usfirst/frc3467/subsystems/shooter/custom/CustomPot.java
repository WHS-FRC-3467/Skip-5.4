package org.usfirst.frc3467.subsystems.shooter.custom;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class CustomPot extends AnalogPotentiometer {
	private double offset = 0;
	
	public CustomPot(int channel, double scale) {
		super(channel, scale);
	}
	
	public double pidGet() {
		return this.get() + offset;
	}
	
	public void setOffset(double offset) {
		this.offset = offset;
	}
	
}
