package org.usfirst.frc3467.subsystems.DriveBase3V3;

import org.usfirst.frc3467.OI;
import org.usfirst.frc3467.RobotMap;
import org.usfirst.frc3467.other.Current;
import org.usfirst.frc3467.pid.Input;
import org.usfirst.frc3467.pid.Output;
import org.usfirst.frc3467.pid.PIDTest;
import org.usfirst.frc3467.subsystems.SubsystemBase;
import org.usfirst.frc3467.subsystems.DriveBase3V3.commands.DriveStraight;
import org.usfirst.frc3467.subsystems.DriveBase3V3.commands.DriveTank;
import org.usfirst.frc3467.subsystems.DriveBase3V3.commands.ResetDBSensors;
import org.usfirst.frc3467.subsystems.DriveBase3V3.commands.ShiftDown;
import org.usfirst.frc3467.subsystems.DriveBase3V3.commands.ShiftUp;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase extends Subsystem implements SubsystemBase {
	
	public static final boolean debugging = false;
	
	public Talon left;
	public Talon right;
	
	public Encoder leftSideEnc;
	public Encoder rightSideEnc;
	public Gyro gyro;
	
	public Current leftCurrent;
	public Current rightCurrent;
	public Current mainBreaker;
	
	public static final double TICKS_PER_REV = 2816;
	public static final double WHEEL_DIAMETER = 4;
	public static final double TICKS_PER_INCH = (TICKS_PER_REV / (WHEEL_DIAMETER * Math.PI)); // 224.2
	public static final double MAX_SPEED = 120; // Inches per second
	
	private static final double Gp = 0.1;
	private static final double Gi = 0.0;
	private static final double Gd = 0.0;
	
	private RobotDrive drive;
	
	public PIDController angle;
	public PIDTest angleTest;
	
	private Solenoid shiftDown;
	private Solenoid shiftUp;
	
	public Output lOutput;
	public Output rOutput;
	public Output gOutput;
	
	public Input avgEnc;
	
	private static DriveBase instance;
	
	public static DriveBase getInstance() {
		return instance;
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(new DriveTank());
	}
	
	public DriveBase() {
		instance = this;
		
		left = new Talon(RobotMap.dbLeft);
		right = new Talon(RobotMap.dbRight);
		
		shiftDown = new Solenoid(RobotMap.shiftDown);
		shiftUp = new Solenoid(RobotMap.shiftUp);
		
		if (debugging) {
			SmartDashboard.putNumber("Max Speed", 0.6);
			SmartDashboard.putNumber("Distance K", 0.237);
		}
		
		leftSideEnc = new Encoder(RobotMap.driveBaseLeftEncoderA, RobotMap.driveBaseLeftEncoderB);
		leftSideEnc.setDistancePerPulse(1 / TICKS_PER_INCH);
		leftSideEnc.setPIDSourceParameter(PIDSourceParameter.kRate);
		leftSideEnc.setReverseDirection(true);
		leftSideEnc.start();
		
		rightSideEnc = new Encoder(RobotMap.driveBaseRightEncoderA, RobotMap.driveBaseRightEncoderB);
		rightSideEnc.setDistancePerPulse(1 / TICKS_PER_INCH);
		rightSideEnc.setPIDSourceParameter(PIDSourceParameter.kRate);
		rightSideEnc.setReverseDirection(true);
		rightSideEnc.start();
		
		gyro = new Gyro(RobotMap.dbGyro);
		
		lOutput = new Output(left, false);
		rOutput = new Output(right, true);
		gOutput = new Output();
		
		drive = new RobotDrive(lOutput, rOutput);
		drive.setSafetyEnabled(false);
		
		angle = new PIDController(Gp, Gi, Gd, gyro, gOutput);
		angle.setContinuous();
		// angleTest = new PIDTest("Gyro", angle, false);
		
		avgEnc = new Input(0);
		avgEnc.addEncoder(leftSideEnc);
		avgEnc.addEncoder(rightSideEnc);
		
		leftCurrent = new Current(RobotMap.leftDBCurrent);
		rightCurrent = new Current(RobotMap.rightDBCurrent);
		// mainBreaker = new Current(RobotMap.mainBreakerCurrent);
	}
	
	public void addButtons() {
		// Add your buttons here
		if (debugging) {
			SmartDashboard.putData("Drive Straight", new DriveStraight(4));
			SmartDashboard.putData("Reset Sensors", new ResetDBSensors());
		}
		Button downShiftBtn = new JoystickButton(OI.leftJoystick, 1);
		downShiftBtn.whenPressed(new ShiftDown());
		Button upShiftBtn = new JoystickButton(OI.rightJoystick, 1);
		upShiftBtn.whenPressed(new ShiftUp());
	}
	
	// Return max speed based on distance to target
	public double getMaxSpeed(int encoderCount, int distanceToGo) {
		double max = SmartDashboard.getNumber("Max Speed", 0.6);
		double maxSpeed = SmartDashboard.getNumber("Distance K", 0.237) * (distanceToGo / 1000);
		if (maxSpeed < -max)
			maxSpeed = -max;
		else if (maxSpeed > max)
			maxSpeed = max;
		if (debugging)
			SmartDashboard.putNumber("Speed", maxSpeed);
		return maxSpeed;
	}
	
	// Use arcade mode to drive
	public void driveArcade(double speed, double angle) {
		drive.arcadeDrive(speed, angle);
		updateSD();
	}
	
	// Use standard tank drive
	public void driveTank(double left, double right) {
		drive.tankDrive(left, right);
		updateSD();
	}
	
	// Refresh Smart Dashboard values
	public void updateSD() {
		if (debugging) {
			SmartDashboard.putNumber("Gyro", gyro.getAngle());
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
		SmartDashboard.putNumber("Left Current", leftCurrent.getCurrent());
		SmartDashboard.putNumber("Right Current", rightCurrent.getCurrent());
		// SmartDashboard.putNumber("Main Breaker", mainBreaker.getCurrent());
	}
	
	// Down shift
	public void shiftDown() {
		shiftDown.set(true);
		shiftUp.set(false);
	}
	
	// Up shift
	public void shiftUp() {
		shiftDown.set(false);
		shiftUp.set(true);
	}
}
