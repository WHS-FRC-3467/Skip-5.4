package org.usfirst.frc3467.subsystems.DriveBase3Test.commands;

import org.usfirst.frc3467.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTurn extends CommandBase {
	
	int degrees = 0;
	
	public DriveTurn(int degrees) {
		requires(db);
		this.setInterruptible(true);
		this.setTimeout(3);
		this.degrees = degrees;
	}
	
	protected void initialize() {
		db.rightSide.enable();
		db.leftSide.enable();
		startCount = db.leftSideEnc.getRaw();
		startCountR = db.rightSideEnc.getRaw();
		this.setTimeout(SmartDashboard.getNumber("Timeout"));
	}
	
	protected void execute() {
		System.out.println("Cruising");
		double speed = SmartDashboard.getNumber("Speed");
		db.driveCruiseControl(speed, -speed);
	}
	
	protected boolean isFinished() {
		return this.isTimedOut();
	}
	
	int startCount = 0;
	int startCountR = 0;
	
	protected void end() {
		System.out.println("Ending Cruise. Went: " + (db.leftSideEnc.getRaw() - startCount) + "      " + (db.rightSideEnc.getRaw() - startCountR));
		db.rightSide.disable();
		db.leftSide.disable();
	}
	
	protected void interrupted() {
		end();
	}
	
}
