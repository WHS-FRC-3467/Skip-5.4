package org.usfirst.frc3467.subsystems.leds;

import org.usfirst.frc3467.commands.CommandBase;

public class UpdateLeds extends CommandBase {
	public UpdateLeds() {
		requires(leds);
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		/*
		 * if (Reverse.reverse) { CommandBase.leds.setState("Reverse", Leds.REG1, 1); } else { CommandBase.leds.setState("Reverse", Leds.REG1, 0); } if (DriveBase.getInstance().mainBreaker.getCurrent() < 150) { if (mast.getDeltaTheta() < 1.0) { if (mast.getSetpoint() == 130.0) { CommandBase.leds.setState("Mast 130", Leds.ONE_THIRTY); } if (mast.getSetpoint() == 122.0) { CommandBase.leds.setState("Mast 122", Leds.TRUSS); } if (mast.getSetpoint() == 115.0) { CommandBase.leds.setState("Mast 115", Leds.CLOSE); } } } else { CommandBase.leds.setState("Current", Leds.HIGH_CURRENT); }
		 */
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
}
