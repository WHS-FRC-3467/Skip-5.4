package org.usfirst.frc3467.subsystems.DriveBase3Test;

import org.usfirst.frc3467.OI;
import org.usfirst.frc3467.RobotMap;
import org.usfirst.frc3467.subsystems.SubsystemBase;
import org.usfirst.frc3467.subsystems.DriveBase3Test.commands.Drive;
import org.usfirst.frc3467.subsystems.DriveBase3Test.commands.DriveCruise;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase extends Subsystem implements SubsystemBase {
	
	private static final double SUBSYSTEM_VERSION = 1.0;
	
	private Talon left;
	private Talon right;
	
	public PIDController leftSide;
	public PIDController rightSide;
	public Encoder leftSideEnc;
	public Encoder rightSideEnc;
	
	protected void initDefaultCommand() {
		// this.setDefaultCommand(new Drive());
	}
	
	private double Kp = 0.0001;
	private double Ki = 0.0;
	private double Kd = 0.0;
	private double Kf = 0.017;
	
	public DriveBase() {
		left = RobotMap.talon2;
		right = RobotMap.talon1;
		
		leftSideEnc = new Encoder(13, 14);
		leftSideEnc.setPIDSourceParameter(PIDSourceParameter.kRate);
		leftSideEnc.setReverseDirection(true);
		leftSideEnc.start();
		leftSide = new PIDController(Kp, Ki, Kd, Kf, leftSideEnc, left);
		// leftSide.enable();
		leftSide.setInputRange(-10000000, 10000000);
		
		rightSideEnc = new Encoder(11, 12);
		rightSideEnc.setPIDSourceParameter(PIDSourceParameter.kRate);
		rightSideEnc.setReverseDirection(true);
		rightSideEnc.start();
		rightSide = new PIDController(Kp, Ki, Kd, Kf, rightSideEnc, right);
		// rightSide.enable();
		rightSide.setInputRange(-10000000, 10000000);
		
		SmartDashboard.putNumber("Lp", Kp);
		SmartDashboard.putNumber("Li", Ki);
		SmartDashboard.putNumber("Ld", Kd);
		SmartDashboard.putNumber("Lf", Kf);
		SmartDashboard.putNumber("LSpeedConstant", 93);
		
		SmartDashboard.putNumber("Rp", Kp);
		SmartDashboard.putNumber("Ri", Ki);
		SmartDashboard.putNumber("Rd", Kd);
		SmartDashboard.putNumber("Rf", Kf);
		SmartDashboard.putNumber("RSpeedConstant", 93);
		
		SmartDashboard.putNumber("Speed", 0.3);
		SmartDashboard.putNumber("Timeout", 3);
	}
	
	public void addButtons() {
		// Add your buttons here
		Button driveCruise = new JoystickButton(OI.leftJoystick, 8); // Creates a button on the joystick trigger
		driveCruise.whenPressed(new DriveCruise());
		
		Button drive = new JoystickButton(OI.leftJoystick, 9); // Creates a button on the joystick trigger
		drive.whenPressed(new Drive());
		
		SmartDashboard.putData("Start", new DriveCruise());
	}
	
	public double getVersion() {
		return SUBSYSTEM_VERSION;
	}
	
	public void drive(double left, double right) {
		this.left.set(left);
		this.right.set(right);
		updateSD();
	}
	
	public void driveCruiseControl(double left, double right) {
		// System.out.print("Entering Drive Control");
		
		double lSpeedConstant = SmartDashboard.getNumber("LSpeedConstant");
		double rSpeedConstant = SmartDashboard.getNumber("RSpeedConstant");
		leftSide.setSetpoint(left * lSpeedConstant);
		rightSide.setSetpoint(right * rSpeedConstant);
		
		// System.out.println(leftSide.getError() + "  " + rightSide.getError());
		// System.out.println(leftSideEnc.getRaw() + "   " + leftSide.get() + "    " + leftSide.getSetpoint());
		updateSD();
	}
	
	private long lastTime = 0L;
	private int lastEncCountLeft = 0;
	private int lastEncCountRight = 0;
	
	private void updateSD() {
		
		// Print data to smart dashboard
		SmartDashboard.putNumber("Left Encoder", leftSideEnc.getRaw());
		SmartDashboard.putNumber("Right Encoder", rightSideEnc.getRaw());
		
		SmartDashboard.putNumber("LeftPidOutput", leftSide.get());
		SmartDashboard.putNumber("RightPidOutput", rightSide.get());
		
		SmartDashboard.putNumber("LeftMotorOutput", left.get());
		SmartDashboard.putNumber("RightMotorOutput", right.get());
		
		SmartDashboard.putNumber("Left PID Error", leftSide.getError());
		SmartDashboard.putNumber("Right PID Error", rightSide.getError());
		
		long currentTime = System.currentTimeMillis(); // Set the current time
		int distanceLeft = leftSideEnc.getRaw() - lastEncCountLeft; // Get delta distance from left side
		int distanceRight = rightSideEnc.getRaw() - lastEncCountRight; // Get delta distance from right side
		int deltaTime = (int) (currentTime - lastTime); // Get delta time from last run
		double speedLeft = (distanceLeft) / deltaTime; // Calculate speed of left side
		double speedRight = -(distanceRight) / deltaTime; // Calculate speed of right side
		
		// Print data to smart dashboard
		SmartDashboard.putNumber("LeftSpeed", speedLeft);
		SmartDashboard.putNumber("RightSpeed", speedRight);
		SmartDashboard.putNumber("AvgSpeed", (speedLeft + speedRight) / 2);
		
		// Reset Varibales
		lastTime = currentTime;
		lastEncCountLeft = leftSideEnc.getRaw();
		lastEncCountRight = rightSideEnc.getRaw();
		
		// Get numbers from textfield in smartdashboard
		double Lp = SmartDashboard.getNumber("Lp");
		double Li = SmartDashboard.getNumber("Li");
		double Ld = SmartDashboard.getNumber("Ld");
		double Lf = SmartDashboard.getNumber("Lf");
		
		double Rp = SmartDashboard.getNumber("Rp");
		double Ri = SmartDashboard.getNumber("Ri");
		double Rd = SmartDashboard.getNumber("Rd");
		double Rf = SmartDashboard.getNumber("Rf");
		
		// Apply new pid values to the pid controller
		leftSide.setPID(Lp, Li, Ld, Lf);
		rightSide.setPID(Rp, Ri, Rd, Rf);
	}
}
