package org.usfirst.frc3467.subsystems.DriveBase3Test.commands;

import org.usfirst.frc3467.commands.CommandBase;

public class Drive extends CommandBase {
	public Drive() {
		requires(db);
		this.setInterruptible(true);
		this.setTimeout(2);
	}
	
	int startCount = 0;
	
	protected void end() {
		db.drive(0.0, 0.0);
		System.out.println("Ending Regular. Went: " + (db.leftSideEnc.getRaw() - startCount));
	}
	
	protected void execute() {
		System.out.println("Regular");
		db.drive(0.3, -0.3);
	}
	
	protected void initialize() {
		startCount = db.leftSideEnc.getRaw();
	}
	
	protected void interrupted() {
		end();
	}
	
	protected boolean isFinished() {
		return this.isTimedOut();
	}
	
}
