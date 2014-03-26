package org.usfirst.frc3467.subsystems.shooter.commands.auto;

import org.usfirst.frc3467.commands.CommandBase;

public class WaitForSignal extends CommandBase {
	double triggerAngle;
	
	public WaitForSignal(double triggerAngle) {
		this.triggerAngle = triggerAngle;
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		
	}
	
	protected boolean isFinished() {
		System.out.println("Arm: " + kinect.armAngle);
		return kinect.getY() > triggerAngle;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
}
