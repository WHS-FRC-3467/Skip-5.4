package org.usfirst.frc3467.subsystems.shooter.commands;

import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.other.Reverse;
import org.usfirst.frc3467.subsystems.shooter.Shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetSetpoint extends CommandBase {
	
	private double setpoint;
	boolean button;
	private boolean ultra;
	
	public SetSetpoint(double setpoint, boolean button) {
		if (setpoint > 90 + Shooter.potRange)
			setpoint = 90 + Shooter.potRange;
		else if (setpoint < 90 - Shooter.potRange)
			setpoint = 90 - Shooter.potRange;
		this.setpoint = setpoint;
		this.button = button;
	}
	
	public SetSetpoint(boolean ultra) {
		this.ultra = ultra;
	}
	
	protected void initialize() {
		if (!ultra) {
			if (!Reverse.reverse)
				shooter.arm.setSetpoint(setpoint);
			else
				shooter.arm.setSetpoint(180 - setpoint);
			if (button) {
				shooter.frontSway = true;
				shooter.backSway = true;
			}
		}
	}
	
	protected void execute() {
		if (ultra) {
			setpoint = ultrasonics.getAngle(ultrasonics.getDistance());
			SmartDashboard.putNumber("Expected Angle", setpoint);
			if (!Reverse.reverse)
				shooter.arm.setSetpoint(setpoint);
			else
				shooter.arm.setSetpoint(180 - setpoint);
			shooter.frontSway = true;
			shooter.backSway = true;
		}
	}
	
	protected boolean isFinished() {
		return !ultra;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
}
