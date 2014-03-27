package org.usfirst.frc3467.subsystems.shooter;

import org.usfirst.frc3467.commands.CommandBase;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Mast extends PIDSubsystem {
	private final static double PERIOD = 0.05;
	private Shooter shooter;
	
	public Mast(double p, double i, double d) {
		super(0, 0, 0, PERIOD, 0);
		shooter = CommandBase.shooter;
		lastAngle = shooter.pot.pidGet();
		lastTime = System.currentTimeMillis();
		setpoint = 90;
	}
	
	protected double returnPIDInput() {
		return 0;
	}
	
	// Update values
	protected void usePIDOutput(double output) {
		// Theta
		theta = shooter.pot.pidGet();
		
		// Delta time
		deltaTime = System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		// Delta theta
		deltaTheta = theta - lastAngle;
		lastAngle = theta;
		
		// Speed
		speed = deltaTheta / deltaTime;
		deltaSpeed = speed - lastSpeed;
		finalSpeed = SPEED_A * Math.sqrt(2.0 * (setpoint - theta));
	}
	
	protected void initDefaultCommand() {
		
	}
	
	private long deltaTime = 0L;
	private double theta = 0.0;
	private double deltaTheta = 0.0;
	private double speed = 0.0;
	private double deltaSpeed = 0.0;
	private double finalSpeed = 0.0;
	private double setpoint = 0.0;
	
	// Holders
	private long lastTime = 0L;
	private double lastAngle = 0.0;
	private double lastSpeed = 0.0;
	
	// Constants
	public double TORQUE_C = 60.0;
	public double TORQUE_A = 0.0;
	private static final double TORQUE_STALL = 0.81 * 660.0;
	private static final double SPEED_RATED = (1608.0 / 660.0) * 360.0 * 60.0;
	private double SPEED_A = 0.0;
	
	public long getDeltaTime() {
		return deltaTime;
	}
	
	public double getDeltaTheta() {
		return deltaTheta;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public double deltaSpeed() {
		return deltaSpeed;
	}
	
	public void setSetpoint(double setpoint) {
		this.setpoint = setpoint;
	}
	
	public double getMotorOutput() {
		// Battery Ratio
		double batteryRatio = (12.0) / (DriverStation.getInstance().getBatteryVoltage());
		
		// Torque Ration
		double torqueRatio;
		// Hold arm
		torqueRatio = (Math.cos(Math.toRadians(theta)) * TORQUE_C) / (TORQUE_STALL);
		// Add speed
		torqueRatio += ((finalSpeed - speed) / deltaTime) * TORQUE_A;
		
		// Speed ratio
		double speedRatio = (speed) / (SPEED_RATED);
		
		// Final output
		double dutyCycle = -(batteryRatio * (torqueRatio + speedRatio));
		
		// Prints
		System.out.println("Theta: " + theta);
		System.out.println("Bat: " + batteryRatio);
		System.out.println("Torque C: " + TORQUE_C);
		System.out.println("Torque A: " + TORQUE_A);
		System.out.println("Tor: " + torqueRatio);
		System.out.println("Speed: " + speedRatio);
		System.out.println("Duty: " + dutyCycle);
		return dutyCycle;
	}
}
