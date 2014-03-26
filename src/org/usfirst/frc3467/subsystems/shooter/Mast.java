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
		setpoint = shooter.arm.getSetpoint();
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
		finalSpeed = Math.sqrt(2 * SPEED_A * (setpoint - theta));
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
	private double TORQUE_C = 0.0;
	private double TORQUE_A = 0.0;
	private static final double TORQUE_STALL = 0.81;
	private static final double SPEED_RATED = 1608 / 660;
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
	
	public double getMotorOutput(boolean stall) {
		double batteryRatio = (12.0) / (DriverStation.getInstance().getBatteryVoltage());
		double torqueRatio;
		if (stall) {
			torqueRatio = (Math.cosh(theta) * TORQUE_C) / (TORQUE_STALL);
		} else {
			torqueRatio = ((finalSpeed - speed) / deltaTime) * TORQUE_A;
		}
		double speedRatio = (speed) / (SPEED_RATED);
		double dutyCycle = batteryRatio * (torqueRatio - speedRatio);
		return dutyCycle;
	}
}
