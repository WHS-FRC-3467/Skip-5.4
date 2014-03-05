package org.usfirst.frc3467.subsystems.shooter.commands;

import org.usfirst.frc3467.commands.CommandBase;

public class ReturnAll extends CommandBase {
	
	public ReturnAll() {
		requires(shooter);
		requires(roller);
	}
	
	protected void initialize() {
		// Disable sway
		shooter.frontSway = false;
		shooter.backSway = false;
		// Return to 90
		shooter.arm.setSetpoint(90.0);
		roller.backArm.setSetpoint(90.0);
		roller.frontArm.setSetpoint(90.0);
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
