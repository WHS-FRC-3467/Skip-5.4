package org.usfirst.frc3467.subsystems.shooter.commands.preset;

import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.subsystems.rollers.Roller;
import org.usfirst.frc3467.subsystems.shooter.Shooter;

public class Sway extends CommandBase {
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		double percent;
		percent = shooter.arm.getSetpoint() / Shooter.potRange;
		if (shooter.arm.getSetpoint() > 90) {
			roller.frontArm.setSetpoint(90 - (Roller.potRange * percent));
			// rollerBack.arm.setSetpoint(90);
		} else if (shooter.arm.getSetpoint() < 90) {
			roller.frontArm.setSetpoint(90);
			// rollerBack.arm.setSetpoint(90 - (RollerBack.potRange * percent));
		} else {
			roller.frontArm.setSetpoint(80);
			// rollerBack.arm.setSetpoint(80);
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
