package org.usfirst.frc3467.subsystems.DriveBase3V3.commands;

import org.usfirst.frc3467.OI;
import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.subsystems.DriveBase3V3.DriveBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends CommandBase {
	DriveBase driveBase;
	int startTicks;
	int distance;
	
	public DriveStraight(int distanceInInches) {
		driveBase = DriveBase.getInstance();
		
		requires(driveBase);
		this.setTimeout(5);
		if (DriveBase.debugging) {
			SmartDashboard.putNumber("DS Timeout", 3);
			SmartDashboard.putNumber("Distance", 12);
		}
	}
	
	protected void initialize() {
		firstRun = true;
		driveBase.gyro.reset();
		driveBase.angle.setSetpoint(0);
		driveBase.angle.enable();
		distance = (int) SmartDashboard.getNumber("Distance", 12);
		startTicks = (int) driveBase.avgEnc.pidGet();
	}
	
	boolean firstRun = true;
	
	protected void execute() {
		if (firstRun) {
			driveBase.driveTank(0.2, 0.2);
			firstRun = false;
		} else {
			this.setTimeout(SmartDashboard.getNumber("DS Timeout", 3));
			driveBase.updateSD();
			// driveBase.angleTest.update();
			int distanceToGo = ((int) ((distance * DriveBase.TICKS_PER_INCH) + startTicks) - (int) driveBase.avgEnc.pidGet());
			driveBase.driveArcade(driveBase.getMaxSpeed((int) driveBase.avgEnc.pidGet(), distanceToGo), driveBase.angle.get());
		}
	}
	
	protected boolean isFinished() {
		int distanceToGo = ((int) ((distance * DriveBase.TICKS_PER_INCH) + startTicks) - (int) driveBase.avgEnc.pidGet());
		if (isTimedOut() || manualSwitch() || Math.abs(distanceToGo) < (DriveBase.TICKS_PER_INCH * 4))
			return true;
		else
			return false;
	}
	
	private boolean manualSwitch() {
		if (Math.abs(OI.leftJoystick.getY()) > 0.2 || Math.abs(OI.rightJoystick.getY()) > 0.2)
			return true;
		else
			return false;
	}
	
	protected void end() {
		driveBase.angle.disable();
		driveBase.driveArcade(0, 0);
		if (DriveBase.debugging)
			SmartDashboard.putNumber("Distance Travled", (Math.abs(driveBase.avgEnc.pidGet()) - startTicks) / DriveBase.TICKS_PER_INCH);
	}
	
	protected void interrupted() {
		end();
	}
}
