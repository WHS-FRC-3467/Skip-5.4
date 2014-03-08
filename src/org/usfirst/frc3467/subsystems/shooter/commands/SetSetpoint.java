package org.usfirst.frc3467.subsystems.shooter.commands;

import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.subsystems.shooter.Shooter;

public class SetSetpoint extends CommandBase {
	
	Shooter shooter;
	private double setpoint;
	boolean button;
	
	public SetSetpoint(double setpoint, boolean button) {
		shooter = Shooter.getInstance();
		if (setpoint > 90 + Shooter.potRange)
			setpoint = 90 + Shooter.potRange;
		else if (setpoint < 90 - Shooter.potRange)
			setpoint = 90 - Shooter.potRange;
		this.setpoint = setpoint;
		this.button = button;
	}
	
	protected void initialize() {
		shooter.arm.setSetpoint(setpoint);
		if (button) {
			shooter.frontSway = true;
			shooter.backSway = true;
		}
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
