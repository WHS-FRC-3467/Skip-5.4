package org.usfirst.frc3467.subsystems.shooter.commands;

import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.subsystems.shooter.Winch;
import org.usfirst.frc3467.subsystems.shooter.commands.winch.WinchIn;

import edu.wpi.first.wpilibj.command.Scheduler;

public class Fire extends CommandBase {
	boolean fullPower = false;
	double power = 0.0;
	
	public Fire() {
		this.fullPower = true;
		requires(winch);
	}
	
	public Fire(double power) {
		fullPower = false;
		this.power = power;
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		if (fullPower) {
			winch.disengageMotor();
			winch.unlockBrake();
		} else {
			winch.engageMotor();
			winch.unlockBrake();
			winch.motor.set(-power);
		}
	}
	
	protected boolean isFinished() {
		return (winch.pot.pidGet() < Winch.minPotValue - 50);
	}
	
	protected void end() {
		System.out.println("Ending Fire");
		winch.motor.set(0);
		Scheduler.getInstance().add(new WinchIn());
	}
	
	protected void interrupted() {
		
	}
	
}
