package org.usfirst.frc3467.subsystems.shooter;

import org.usfirst.frc3467.OI;
import org.usfirst.frc3467.RobotMap;
import org.usfirst.frc3467.control.Gamepad;
import org.usfirst.frc3467.pid.Output;
import org.usfirst.frc3467.pid.PIDTest;
import org.usfirst.frc3467.subsystems.SubsystemBase;
import org.usfirst.frc3467.subsystems.shooter.commands.DriveAngle;
import org.usfirst.frc3467.subsystems.shooter.commands.SetSetpoint;
import org.usfirst.frc3467.subsystems.shooter.custom.CustomPot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem implements SubsystemBase {
	public Talon angleMotor;
	public CustomPot pot;
	public PIDController arm;
	public Output motorOutput;
	
	public static final boolean debugging = true;
	
	private static double Kp = 0.018;
	private static double Ki = 0.00;
	private static double Kd = 0.00;
	
	public static final int potRange = 45; // Degrees from 90
	
	private static Shooter instance;
	
	public static Shooter getInstance() {
		return instance;
	}
	
	public PIDTest test;
	
	public Shooter() {
		instance = this;
		angleMotor = new Talon(RobotMap.armAngle);
		motorOutput = new Output(angleMotor, true);
		pot = new CustomPot(RobotMap.armPot, (300 / 5));
		pot.setOffset(90 - pot.get());
		arm = new PIDController(Kp, Ki, Kd, pot, motorOutput);
		arm.setOutputRange(-RobotMap.armMaxSpeed, RobotMap.armMaxSpeed);
		arm.setSetpoint(90);
		if (debugging)
			test = new PIDTest("Arm", arm, false);
	}
	
	public void addButtons() {
		// Shooting
		Button lb = new JoystickButton(OI.oppGamepadAuto, Gamepad.leftBumper);
		lb.whenPressed(new SetSetpoint(54));
		// Loading
		Button rb = new JoystickButton(OI.oppGamepadAuto, Gamepad.leftTrigger);
		rb.whenPressed(new SetSetpoint(45));
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(new DriveAngle());
	}
	
}
