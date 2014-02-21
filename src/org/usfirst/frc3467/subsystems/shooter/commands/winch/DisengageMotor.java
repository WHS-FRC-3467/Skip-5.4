package org.usfirst.frc3467.subsystems.shooter.commands.winch;

import org.usfirst.frc3467.commands.CommandBase;

public class DisengageMotor extends CommandBase {
	public DisengageMotor() {
		
	}
	
	protected void initialize() {
		winch.disengageMotor();
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
