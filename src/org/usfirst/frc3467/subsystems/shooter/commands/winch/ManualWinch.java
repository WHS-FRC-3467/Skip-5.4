package org.usfirst.frc3467.subsystems.shooter.commands.winch;

import org.usfirst.frc3467.OI;
import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.subsystems.shooter.Winch;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualWinch extends CommandBase {
	
	public ManualWinch() {
		requires(winch);
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		winch.motor.set(OI.oppGamepadManu.getY());
		SmartDashboard.putNumber("POT!!!", Winch.getInstance().pot.pidGet());
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
}
