package org.usfirst.frc3467.subsystems.DriveBase3;

import org.usfirst.frc3467.RobotMap;
import org.usfirst.frc3467.subsystems.SubsystemBase;
import org.usfirst.frc3467.subsystems.DriveBase3.commands.Drive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase extends Subsystem implements SubsystemBase {
	
	private static final double SUBSYSTEM_VERSION = 1.0;
	
	private Talon left;
	private Talon right;
	
	public PIDController leftSide;
	public PIDController rightSide;
	private Encoder leftSideEnc;
	private Encoder rightSideEnc;
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(new Drive());
	}
	
	private double Kp = 0.0001;
	private double Ki = 0.0;
	private double Kd = 0.0;
	
	private double Kf = 0.01;
	
	public DriveBase() {
		left = RobotMap.talon2;
		right = RobotMap.talon1;
		
		leftSideEnc = new Encoder(13, 14);
		leftSideEnc.setPIDSourceParameter(PIDSourceParameter.kRate);
		leftSideEnc.setReverseDirection(true);
		leftSideEnc.start();
		leftSide = new PIDController(Kp, Ki, Kd, Kf, leftSideEnc, left);
		leftSide.enable();
		leftSide.setInputRange(-10000000, 10000000);
		
		rightSideEnc = new Encoder(11, 12);
		rightSideEnc.setPIDSourceParameter(PIDSourceParameter.kRate);
		rightSideEnc.setReverseDirection(true);
		rightSideEnc.start();
		rightSide = new PIDController(Kp, Ki, Kd, Kf, rightSideEnc, right);
		rightSide.enable();
		rightSide.setInputRange(-10000000, 10000000);
		
		SmartDashboard.putNumber("P", Kp);
		SmartDashboard.putNumber("I", Ki);
		SmartDashboard.putNumber("D", Kd);
		SmartDashboard.putNumber("F", Kf);
	}
	
	public void addButtons() {
		// Add your buttons here
		// Button toggleCruise = new JoystickButton(OI.leftJoystick, 1); // Creates a button on the joystick trigger
		// toggleCruise.whenReleased(new DriveCruise());
	}
	
	public double getVersion() {
		return SUBSYSTEM_VERSION;
	}
	
	public void drive(double left, double right) {
		
	}
	
	public void driveCruiseControl(double left, double right) {
		// System.out.print("Entering Drive Control");
		int speedConstant = 93;
		leftSide.setSetpoint(-left * speedConstant);
		rightSide.setSetpoint(right * speedConstant);
		// System.out.print("    " + leftSideEnc.pidGet());
		// System.out.println("  " + leftSide.getError());
		// System.out.println(leftSideEnc.getRaw() + "   " + leftSide.get() + "    " + leftSide.getSetpoint());
		updateSD();
		
	}
	
	public void updateSD() {
		SmartDashboard.putNumber("Left Encoder", leftSideEnc.getRaw());
		SmartDashboard.putNumber("Right Encoder", rightSideEnc.getRaw());
		SmartDashboard.putNumber("LeftPidOutput", leftSide.get());
		SmartDashboard.putNumber("RightPidOutput", rightSide.get());
		// SmartDashboard.putNumber("Setpoint", leftSide.getSetpoint());
		
		double p = SmartDashboard.getNumber("P");
		double i = SmartDashboard.getNumber("I");
		double d = SmartDashboard.getNumber("D");
		double f = SmartDashboard.getNumber("F");
		leftSide.setPID(p, i, d, f);
	}
}
