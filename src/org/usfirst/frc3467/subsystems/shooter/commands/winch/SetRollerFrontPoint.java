package org.usfirst.frc3467.subsystems.shooter.commands.winch;

import org.usfirst.frc3467.commands.CommandBase;

public class SetRollerFrontPoint extends CommandBase {
	double angle = 90;
	
	public SetRollerFrontPoint(int angle) {
		this.setTimeout(2);
		this.angle = angle;
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
		roller.frontArm.setSetpoint(angle);
	}
	
	protected void execute() {
		// TODO Auto-generated method stub
		
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end() {
		// TODO Auto-generated method stub
		
	}
	
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
}
