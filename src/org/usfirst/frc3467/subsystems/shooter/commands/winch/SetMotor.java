package org.usfirst.frc3467.subsystems.shooter.commands.winch;

import org.usfirst.frc3467.commands.CommandBase;

public class SetMotor extends CommandBase {
	double speed = 0.0;
	
	public SetMotor(double speed) {
		this.speed = speed;
	}
	
	protected void initialize() {
		winch.motor.set(speed);
	}
	
	protected void execute() {
		// TODO Auto-generated method stub
		
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	
	protected void end() {
		// TODO Auto-generated method stub
		
	}
	
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
}
