package org.usfirst.frc3467.subsystems.shooter.commands.winch;

import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.subsystems.shooter.Winch;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WinchIn extends CommandBase {
	
	public WinchIn() {
		requires(winch);
	}
	
	protected void initialize() {
		if (winch.pot.pidGet() < Winch.maxPotValue) {
			winch.engageMotor();
			winch.unlockBrake();
		}
	}
	
	protected void execute() {
		if (roller.frontPot.pidGet() > 80)
			this.cancel();
		else if (winch.pot.pidGet() < Winch.maxPotValue)
			winch.motor.set(1.0);
		SmartDashboard.putNumber("POT!!!", Winch.getInstance().pot.pidGet());
	}
	
	protected boolean isFinished() {
		return this.isCanceled() || (winch.pot.pidGet() > Winch.maxPotValue);
	}
	
	protected void end() {
		Scheduler.getInstance().add(new EndWinch());
	}
	
	protected void interrupted() {
		end();
	}
	
}
