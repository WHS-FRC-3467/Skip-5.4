package org.usfirst.frc3467.subsystems.shooter.commands.winch;

import org.usfirst.frc3467.commands.CommandBase;

public class UnlockBrake extends CommandBase {
	
	public UnlockBrake() {
		
	}
	
	protected void initialize() {
		winch.unlockBrake();
	}
	
	protected void execute() {
		
	}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
}
