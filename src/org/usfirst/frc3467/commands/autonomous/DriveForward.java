package org.usfirst.frc3467.commands.autonomous;

import org.usfirst.frc3467.commands.CommandBase;

public class DriveForward extends CommandBase {
	
	public DriveForward() {
		requires(db);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	protected void execute() {
		db.driveTank(0.7, 0.7);
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end() {
		// TODO Auto-generated method stub
		
	}
	
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
}
