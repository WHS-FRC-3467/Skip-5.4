package org.usfirst.frc3467.subsystems.rollers.commands;

import org.usfirst.frc3467.OI;
import org.usfirst.frc3467.commands.CommandBase;

public class SetRollerDirection extends CommandBase {
	double power = 0.0;
	
	public SetRollerDirection(double direction) {
		this.power = direction;
		requires(rollies);
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		if (OI.opGamepadAuto.getRightBumper()) {
			rollies.rollerFront.set(power);
		} else if (OI.opGamepadAuto.getRightTrigger()) {
			rollies.rollerBack.set(-power);
		}
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
}
