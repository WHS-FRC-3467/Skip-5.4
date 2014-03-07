package org.usfirst.frc3467.subsystems.shooter;

import org.usfirst.frc3467.RobotMap;
import org.usfirst.frc3467.subsystems.SubsystemBase;
import org.usfirst.frc3467.subsystems.shooter.commands.winch.ManualWinch;
import org.usfirst.frc3467.subsystems.shooter.custom.CustomPot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem implements SubsystemBase {
	public Talon motor;
	public CustomPot pot;
	public PIDController winch;
	private Solenoid unlockBrake;
	private Solenoid lockBrake;
	private Solenoid engageMotor;
	private Solenoid disengageMotor;
	
	public static int maxPotValue = 3400;
	public static int minPotValue = 2000;
	public static int difference = 1380;
	
	public static Winch instance;
	
	public static Winch getInstance() {
		return instance;
	}
	
	private final double Kp = 0.0;
	private final double Ki = 0.0;
	private final double Kd = 0.0;
	
	public Winch() {
		instance = this;
		motor = new Talon(RobotMap.winch);
		pot = new CustomPot(RobotMap.winchPot, 3600 / 5);
		winch = new PIDController(Kp, Ki, Kd, pot, motor);
		winch.setSetpoint(0);
		winch.disable();
		unlockBrake = new Solenoid(RobotMap.winchBrakeRelease);
		lockBrake = new Solenoid(RobotMap.winchBrakeLock);
		engageMotor = new Solenoid(RobotMap.winchShiftIn);
		disengageMotor = new Solenoid(RobotMap.winchShiftOut);
		minPotValue = (int) pot.pidGet();
		maxPotValue = (int) pot.pidGet() + difference;
		
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ManualWinch());
	}
	
	public void addButtons() {
		// Standard full power shot
		// Button fire = new JoystickButton(OI.rightJoystick, 3);
		// fire.whenPressed(new Fire());
		// Shot where motor is still engaged
		// Button fireSoft = new JoystickButton(OI.leftJoystick, 3);
		// fireSoft.whenPressed(new Fire(1.0));
		// Winch in shooter
		// Button winchIn = new JoystickButton(OI.opGamepadAuto, Gamepad.startButton);
		// winchIn.whenPressed(new WinchIn());
		
		// Button safeRelease = new JoystickButton(OI.leftJoystick, 11);
		// safeRelease.whenPressed(new SafeRelease());
	}
	
	public void lockBrake() {
		System.out.println("Locking Break");
		lockBrake.set(true);
		unlockBrake.set(false);
	}
	
	public void unlockBrake() {
		System.out.println("Unlocking Brake");
		unlockBrake.set(true);
		lockBrake.set(false);
	}
	
	public void engageMotor() {
		System.out.println("Engaging Motor");
		engageMotor.set(true);
		disengageMotor.set(false);
	}
	
	public void disengageMotor() {
		System.out.println("Disengaging Motor");
		disengageMotor.set(true);
		engageMotor.set(false);
	}
}
