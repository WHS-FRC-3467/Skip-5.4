package org.usfirst.frc3467.subsystems.shooter;

import org.usfirst.frc3467.commands.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveNew extends CommandBase {
	
	public DriveNew() {
		requires(mast);
		requires(shooter);
	}
	
	protected void initialize() {
		SmartDashboard.putNumber("TORQUE_C", mast.TORQUE_C);
		SmartDashboard.putNumber("TORQUE_A", mast.TORQUE_A);
		mast.enable();
	}
	
	protected void execute() {
		mast.TORQUE_C = SmartDashboard.getNumber("TORQUE_C");
		mast.TORQUE_A = SmartDashboard.getNumber("TORQUE_A");
		shooter.angleMotor.set(mast.getMotorOutput());
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
}
