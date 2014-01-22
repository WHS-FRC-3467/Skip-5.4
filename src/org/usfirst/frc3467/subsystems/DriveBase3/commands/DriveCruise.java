package org.usfirst.frc3467.subsystems.DriveBase3.commands;

import org.usfirst.frc3467.commands.CommandBase;

public class DriveCruise extends CommandBase {
	public static boolean cruise = false;
	
	public DriveCruise() {
		
	}
	
	protected void initialize() {
		cruise = !cruise;
		System.out.println("Chaning cruise state to: " + cruise);
	}
	
	protected void execute() {
		// db.driveCruiseControl(OI.leftJoystick.getThrottle(), 0.0);
	}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
}
