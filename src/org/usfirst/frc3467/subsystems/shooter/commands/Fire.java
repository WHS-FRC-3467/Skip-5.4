package org.usfirst.frc3467.subsystems.shooter.commands;

import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.subsystems.shooter.Winch;

public class Fire extends CommandBase {
	boolean fullPower = true;
	double power = 0.0;
	
	public Fire(double power, boolean fullPower) {
		requires(winch);
		this.fullPower = fullPower;
		this.power = power;
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		if (fullPower) {
			System.out.println("FULL POWER SHOT");
			winch.disengageMotor();
		} else {
			System.out.println("SHOFT SHOT");
			winch.engageMotor();
		}
		winch.unlockBrake();
		winch.motor.set(-power);
	}
	
	protected boolean isFinished() {
		return (winch.pot.pidGet() < Winch.minPotValue - 60);
	}
	
	protected void end() {
		System.out.println("Ending Fire");
		winch.motor.set(0.0);
	}
	
	protected void interrupted() {
		
	}
	
}
