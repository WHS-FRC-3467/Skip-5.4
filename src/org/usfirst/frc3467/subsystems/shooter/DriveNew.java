package org.usfirst.frc3467.subsystems.shooter;

import org.usfirst.frc3467.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveNew extends CommandBase {
	
	public DriveNew() {
		requires(mast);
		requires(shooter);
	}
	
	protected void initialize() {
		SmartDashboard.putNumber("TORQUE C", 0.0);
		mast.enable();
	}
	
	protected void execute() {
		mast.TORQUE_A = SmartDashboard.getNumber("TORQUE C", 0.0);
		System.out.println(mast.getMotorOutput(true));
		shooter.angleMotor.set(mast.getMotorOutput(true));
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
}
