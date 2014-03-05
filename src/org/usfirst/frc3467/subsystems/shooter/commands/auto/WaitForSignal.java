package org.usfirst.frc3467.subsystems.shooter.commands.auto;

import org.usfirst.frc3467.commands.CommandBase;

public class WaitForSignal extends CommandBase {
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		
	}
	
	protected boolean isFinished() {
		return kinect.armAngle > 0.9;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
}
