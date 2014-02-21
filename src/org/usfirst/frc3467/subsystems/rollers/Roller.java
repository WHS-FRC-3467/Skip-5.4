package org.usfirst.frc3467.subsystems.rollers;

import org.usfirst.frc3467.RobotMap;
import org.usfirst.frc3467.pid.Output;
import org.usfirst.frc3467.pid.PIDTest;
import org.usfirst.frc3467.subsystems.SubsystemBase;
import org.usfirst.frc3467.subsystems.rollers.commands.DrivePickupAngle;
import org.usfirst.frc3467.subsystems.rollers.custom.CustomPot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Roller extends Subsystem implements SubsystemBase {
	public Talon frontMotor;
	public Talon backMotor;
	public Talon rollerFront;
	public Talon rollerBack;
	public CustomPot frontPot;
	public CustomPot backPot;
	public PIDController frontArm;
	public PIDController backArm;
	public PIDTest frontTest;
	public PIDTest backTest;
	public Output frontMotorOutput;
	public Output backMotorOutput;
	
	public static final boolean frontRoller = true;
	public static final boolean backRoller = true;
	
	public static final boolean debugging = true;
	
	private static double Kp = 0.03;
	private static double Ki = 0.00;
	private static double Kd = 0.00;
	
	public static final int potRange = 58; // Degrees
	
	private static Roller instance;
	
	public static Roller getInstance() {
		return instance;
	}
	
	public Roller() {
		instance = this;
		
		frontMotor = new Talon(RobotMap.pickupTalonFront);
		frontMotorOutput = new Output(frontMotor, true);
		rollerFront = new Talon(RobotMap.rollerTalonFront);
		frontPot = new CustomPot(RobotMap.rollerFrontPot, ((300.0 / 5.0) * (11.0 / 22.0)));
		frontPot.setOffset(90 - frontPot.get());
		frontArm = new PIDController(Kp, Ki, Kd, frontPot, frontMotorOutput);
		frontArm.setOutputRange(-RobotMap.pickUpMaxSpeed, RobotMap.pickUpMaxSpeed);
		frontArm.setSetpoint(90);
		frontTest = new PIDTest("Front Pickup Arm", frontArm, false);
		
		backMotor = new Talon(RobotMap.pickupTalonBack);
		backMotorOutput = new Output(backMotor, true);
		rollerBack = new Talon(RobotMap.rollerTalonBack);
		backPot = new CustomPot(RobotMap.rollerBackPot, ((300.0 / 5.0) * (11.0 / 22.0)));
		backPot.setOffset(90 - backPot.get());
		backArm = new PIDController(Kp, Ki, Kd, backPot, backMotorOutput);
		backArm.setOutputRange(-RobotMap.pickUpMaxSpeed, RobotMap.pickUpMaxSpeed);
		backArm.setSetpoint(90);
		backTest = new PIDTest("Back Pickup Arm", backArm, false);
	}
	
	public void addButtons() {
		
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(new DrivePickupAngle());
	}
	
}
