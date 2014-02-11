package org.usfirst.frc3467.subsystems.DriveBase3V3;

import org.usfirst.frc3467.OI;
import org.usfirst.frc3467.RobotMap;
import org.usfirst.frc3467.subsystems.SubsystemBase;
import org.usfirst.frc3467.subsystems.DriveBase3V3.commands.Drive;
import org.usfirst.frc3467.subsystems.DriveBase3V3.commands.DriveAtSpeed;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase extends Subsystem implements SubsystemBase {
	
	private static final double SUBSYSTEM_VERSION = 1.0;
	
	public Talon left;
	public Talon right;
	
	public Encoder leftSideEnc;
	public Encoder rightSideEnc;
	
	public DriveAtSpeed das;
	
	public static final double TICKS_PER_REV = 2816;
	public static final double WHEEL_DIAMETER = 4;
	public static final double TICKS_PER_INCH = (TICKS_PER_REV / (WHEEL_DIAMETER * Math.PI));
	public static final double MAX_SPEED = 120; // Inches per second
	
	protected void initDefaultCommand() {
		
	}
	
	public DriveBase() {
		left = RobotMap.talon2;
		right = RobotMap.talon1;
		
		leftSideEnc = new Encoder(13, 14);
		leftSideEnc.setDistancePerPulse(1 / TICKS_PER_INCH);
		leftSideEnc.setPIDSourceParameter(PIDSourceParameter.kRate);
		leftSideEnc.setReverseDirection(true);
		leftSideEnc.start();
		// leftSide.enable();
		
		rightSideEnc = new Encoder(11, 12);
		rightSideEnc.setDistancePerPulse(1 / TICKS_PER_INCH);
		rightSideEnc.setPIDSourceParameter(PIDSourceParameter.kRate);
		rightSideEnc.setReverseDirection(true);
		rightSideEnc.start();
		
		// SmartDashboard.putNumber("Timeout", 3);
	}
	
	public void addButtons() {
		// Add your buttons here
		das = new DriveAtSpeed();
		Button driveCruise = new JoystickButton(OI.leftJoystick, 8); // Creates a button on the joystick trigger
		driveCruise.whenPressed(new Drive());
		
		Button drive = new JoystickButton(OI.leftJoystick, 9); // Creates a button on the joystick trigger
		drive.whenPressed(new Drive());
		
		SmartDashboard.putData("Start", new Drive());
	}
	
	public double getVersion() {
		return SUBSYSTEM_VERSION;
	}
	
	public void drive(double left, double right) {
		
	}
	
	public void driveAtSpeed(double left, double right) {
		das.setSpeed(left, right);
	}
	
	public void updateSD() {
		
		// Print data to smart dashboard
		SmartDashboard.putNumber("Left Encoder", leftSideEnc.getRaw());
		SmartDashboard.putNumber("Right Encoder", rightSideEnc.getRaw());
		
		SmartDashboard.putNumber("LeftMotorOutput", left.get());
		SmartDashboard.putNumber("RightMotorOutput", right.get());
		
		// Print data to smart dashboard
		SmartDashboard.putNumber("LeftSpeed", leftSideEnc.getRate());
		SmartDashboard.putNumber("RightSpeed", rightSideEnc.getRate());
		SmartDashboard.putNumber("AvgSpeed", (rightSideEnc.getRate() + leftSideEnc.getRate()) / 2);
	}
	
	public void driveCruiseControl(double y, double y2) {
		// TODO Auto-generated method stub
		
	}
}
