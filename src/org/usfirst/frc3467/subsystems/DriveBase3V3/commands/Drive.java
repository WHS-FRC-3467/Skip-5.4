package org.usfirst.frc3467.subsystems.DriveBase3V3.commands;

import org.usfirst.frc3467.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends CommandBase {
	public Drive() {
		requires(db);
		this.setTimeout(3);
		this.setInterruptible(true);
		SmartDashboard.putNumber("Timeout", 3);
	}
	
	protected void end() {
		db.das.end();
	}
	
	protected void execute() {
		db.das.setSpeed(0.3, 0.3);
	}
	
	protected void initialize() {
		this.setTimeout(SmartDashboard.getNumber("Timeout"));
		db.das.initialize();
		
	}
	
	protected void interrupted() {
		end();
	}
	
	protected boolean isFinished() {
		return this.isTimedOut();
	}
	
}
