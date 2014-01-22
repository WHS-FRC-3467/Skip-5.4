package org.usfirst.frc3467.subsystems.DriveBase2.commands;

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
		SmartDashboard.putNumber("Left Joystick", OI.leftJoystick.getY());
		SmartDashboard.putNumber("Right Joystick", OI.rightJoystick.getY());
		db.drive(OI.leftJoystick.getY(), OI.rightJoystick.getY());
	}
	
	protected void initialize() {
		
	}
	
	protected void interrupted() {
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
}
