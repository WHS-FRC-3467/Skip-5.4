package org.usfirst.frc3467.subsystems.DriveBase3.commands;

import org.usfirst.frc3467.OI;
import org.usfirst.frc3467.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends CommandBase {
	public Drive() {
		requires(db);
	}
	
	protected void end() {
		
	}
	
	protected void execute() {
		SmartDashboard.putNumber("Left Joystick", OI.joystick1.getY());
		SmartDashboard.putNumber("Right Joystick", OI.joystick2.getY());
		db.drive(OI.joystick1.getY(), OI.joystick2.getY());
	}
	
	protected void initialize() {
		
	}
	
	protected void interrupted() {
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
}
