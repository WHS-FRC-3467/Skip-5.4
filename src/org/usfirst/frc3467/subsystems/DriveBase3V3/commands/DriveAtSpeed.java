package org.usfirst.frc3467.subsystems.DriveBase3V3.commands;

import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.interfaces.Output;
import org.usfirst.frc3467.pid.PIDTest;
import org.usfirst.frc3467.subsystems.DriveBase3V3.DriveBase;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveAtSpeed {
	
	DriveBase db = CommandBase.db;
	
	private PIDController pidLeft;
	private PIDController pidRight;
	
	private double Kp = 0.015;
	private double Ki = 0.0;
	private double Kd = 0.0;
	private double Kf = 0.027;
	
	PIDTest pidLeftTest;
	PIDTest pidRightTest;
	
	public DriveAtSpeed() {
		pidLeft = new PIDController(Kp, Ki, Kd, Kf, db.leftSideEnc, db.left);
		pidRight = new PIDController(Kp, Ki, Kd, Kf, db.rightSideEnc, new Output(db.right));
		
		pidLeftTest = new PIDTest("Left", pidLeft, true);
		pidRightTest = new PIDTest("Right", pidRight, true);
		// SmartDashboard.putNumber("Lp", Kp);
		// SmartDashboard.putNumber("Li", Ki);
		// SmartDashboard.putNumber("Ld", Kd);
		// SmartDashboard.putNumber("Lf", Kf);
		
		// SmartDashboard.putNumber("Rp", Kp);
		// SmartDashboard.putNumber("Ri", Ki);
		// SmartDashboard.putNumber("Rd", Kd);
		// SmartDashboard.putNumber("Rf", Kf);
		
		// SmartDashboard.putNumber("Max Speed", 0.001);
		// SmartDashboard.putNumber("Timeout", 3);
		// SmartDashboard.putNumber("Distance", 144 * DriveBase.TICKS_PER_INCH);
		SmartDashboard.putNumber("Max Speed", 30);
	}
	
	int count = 0;
	
	public void initialize() {
		pidLeft.enable();
		pidRight.enable();
		count = 0;
		System.out.println("Drive@Speed: Initializing ");
	}
	
	private void updateSD() {
		// Get numbers from text field in smart dashboard
		/*
		 * double Lp = SmartDashboard.getNumber("Lp"); double Li = SmartDashboard.getNumber("Li"); double Ld = SmartDashboard.getNumber("Ld"); double Lf = SmartDashboard.getNumber("Lf"); double Rp = SmartDashboard.getNumber("Rp"); double Ri = SmartDashboard.getNumber("Ri"); double Rd = SmartDashboard.getNumber("Rd"); double Rf = SmartDashboard.getNumber("Rf"); pidLeft.setPID(Lp, Li, Ld, Lf); pidRight.setPID(Rp, Ri, Rd, Rf);
		 */
		
		System.out.println("Drive@Speed: Updating PID");
	}
	
	public void setSpeed(double leftSpeed, double rightSpeed) {
		if (count < 3) {
			db.left.set(0.15);
			db.right.set(0.15);
			count++;
		} else {
			leftSpeed = leftSpeed * getMaxSpeed();
			rightSpeed = rightSpeed * getMaxSpeed();
			updateSD();
			pidLeftTest.update();
			pidRightTest.update();
			db.updateSD();
			pidLeft.setSetpoint(leftSpeed);
			pidRight.setSetpoint(rightSpeed);
			System.out.println("Drive@Speed: Setting speed");
			SmartDashboard.putNumber("LeftError", pidLeft.getError());
			SmartDashboard.putNumber("RightError", pidRight.getError());
		}
	}
	
	private double getMaxSpeed() {
		return SmartDashboard.getNumber("Max Speed");
	}
	
	protected void end() {
		pidLeft.disable();
		pidRight.disable();
		db.left.set(0.0);
		db.right.set(0.0);
		System.out.println("Drive@Speed: Ending");
	}
	
}
