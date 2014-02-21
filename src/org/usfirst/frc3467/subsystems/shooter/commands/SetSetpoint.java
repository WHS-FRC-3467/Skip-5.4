package org.usfirst.frc3467.subsystems.shooter.commands;

import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.subsystems.shooter.Shooter;

public class SetSetpoint extends CommandBase {
	
	Shooter shooter;
	private double setpoint;
	
	public SetSetpoint(double setpoint) {
		shooter = Shooter.getInstance();
		if (setpoint > 90 + Shooter.potRange)
			setpoint = 90 + Shooter.potRange;
		else if (setpoint < 90 - Shooter.potRange)
			setpoint = 90 - Shooter.potRange;
		this.setpoint = setpoint;
	}
	
	protected void initialize() {
		shooter.arm.setSetpoint(setpoint);
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
