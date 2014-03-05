package org.usfirst.frc3467.subsystems.rollers.commands;

import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.subsystems.rollers.Roller;

public class SetSetpoint extends CommandBase {
	
	Roller roller;
	private double setpoint;
	private boolean front = false;
	
	public SetSetpoint(double setpoint, boolean front) {
		roller = Roller.getInstance();
		if (setpoint > Roller.potRange)
			setpoint = Roller.potRange;
		else if (setpoint < 32)
			setpoint = 32;
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
