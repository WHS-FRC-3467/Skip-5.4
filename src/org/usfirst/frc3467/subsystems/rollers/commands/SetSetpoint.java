package org.usfirst.frc3467.subsystems.rollers.commands;

import org.usfirst.frc3467.commands.CommandBase;

public class SetSetpoint extends CommandBase {
	
	private double setpoint;
	private boolean front = false;
	
	public SetSetpoint(double setpoint, boolean front) {
		this.setpoint = setpoint;
	}
	
	protected void initialize() {
		if (front)
			roller.frontArm.setSetpoint(setpoint);
		else
			roller.backArm.setSetpoint(setpoint);
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
