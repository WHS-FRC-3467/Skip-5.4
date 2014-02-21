package org.usfirst.frc3467.subsystems.rollers;

import org.usfirst.frc3467.commands.CommandBase;

public class ResetPot extends CommandBase {
	
	protected void initialize() {
		Roller.getInstance().frontPot.setOffset(90.0 - Roller.getInstance().frontPot.get());
	}
	
	protected void execute() {
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
}
