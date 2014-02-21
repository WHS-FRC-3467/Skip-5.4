package org.usfirst.frc3467.subsystems.shooter.commands;

import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.subsystems.shooter.Shooter;

import edu.wpi.first.wpilibj.command.Scheduler;

public class ToggleAngle extends CommandBase {
	
	public static int point = 0;
	public static int range = 1;
	private boolean subtract = false;
	
	public ToggleAngle(boolean subtract) {
		this.subtract = subtract;
	}
	
	private final boolean absolute = false;
	private final int scale = 45;
	
	protected void initialize() {
		int angle = (int) Shooter.getInstance().arm.getSetpoint();
		if (absolute)
			if (subtract) {
				point -= 1;
			} else {
				point += 1;
			}
		else if (subtract)
			point = ((angle - scale) / scale - 2);
		else if (!subtract)
			point = ((angle + scale) / scale - 2);
		if (point > range)
			point = range;
		else if (point < -range)
			point = -range;
		angle = 90;
		switch (point) {
			case -1:
				angle = 45;
				break;
			case 0:
				angle = 90;
				break;
			case 1:
				angle = 135;
				break;
			default:
				angle = 90;
				break;
		}
		Scheduler.getInstance().add(new SetSetpoint(angle));
	}
	
	protected void execute() {
		
	}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
	private int round(int unrounded, int scale) {
		return 0;
	}
}
