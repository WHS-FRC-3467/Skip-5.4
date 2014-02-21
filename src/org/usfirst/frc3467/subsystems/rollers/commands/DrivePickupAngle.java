package org.usfirst.frc3467.subsystems.rollers.commands;

import org.usfirst.frc3467.OI;
import org.usfirst.frc3467.RobotMap;
import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.subsystems.rollers.Roller;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivePickupAngle extends CommandBase {
	
	Roller roller;
	private static final double range = 20;
	
	public DrivePickupAngle() {
		roller = Roller.getInstance();
		requires(roller);
	}
	
	protected void initialize() {
		if (Roller.debugging) {
			// Front
			SmartDashboard.putNumber("Front Power", 0.3);
			// Back
			SmartDashboard.putNumber("Back Power", 0.3);
		}
	}
	
	protected void execute() {
		if (Roller.debugging) {
			// Front Roller
			roller.frontTest.update();
			SmartDashboard.putNumber("Pot", roller.frontPot.pidGet());
			SmartDashboard.putNumber("Final Output", roller.frontMotor.get());
			RobotMap.pickUpMaxSpeed = SmartDashboard.getNumber("Front Power", 0.3);
			
			// Back Roller
			roller.backTest.update();
			SmartDashboard.putNumber("Back Pot", roller.backPot.pidGet());
			SmartDashboard.putNumber("Back Final Output", roller.backMotor.get());
			RobotMap.pickUpMaxSpeed = SmartDashboard.getNumber("Back Power", 0.3);
		}
		
		// FRONT ROLLERS
		// Manually add joystick values to setpoint
		// Change setpoint by 5 degrees at most
		double maxDegrees = 3;
		int frontDirection = 1;
		int backDirection = -1;
		
		if (Roller.frontRoller) {
			double frontSetpoint = roller.frontArm.getSetpoint();
			if (Math.abs(OI.oppGamepadAuto.getRightStickY()) > 0.1)
				frontSetpoint += (OI.oppGamepadAuto.getRightStickY() * maxDegrees);
			
			// Make sure setpoint is within range
			if (frontSetpoint > 90)
				frontSetpoint = 90;
			else if (frontSetpoint < 90 - Roller.potRange)
				frontSetpoint = 90 - Roller.potRange;
			roller.frontArm.setSetpoint(frontSetpoint);
			
			// Determine the direction that the arm will be moving
			if (roller.frontArm.getError() < 0)
				frontDirection = -frontDirection;
			// If not within PID range
			if (roller.frontPot.pidGet() > (roller.frontArm.getSetpoint() + range) || roller.frontPot.pidGet() < (roller.frontArm.getSetpoint() - range)) {
				// Resets variables in PID and disables PID Controller
				if (roller.frontArm.isEnable())
					roller.frontArm.reset();
				// Drive motor at full speed in the correct direction
				roller.frontMotor.set(RobotMap.pickUpMaxSpeed * frontDirection);
			} else { // If within range
				// Enable PID
				if (!roller.frontArm.isEnable())
					roller.frontArm.enable();
			}
			
			if (roller.frontArm.getSetpoint() < 85)
				roller.rollerFront.set(1.0);
			else
				roller.rollerFront.set(0.0);
		}
		// FRONT ROLLERS
		// Manually add joystick values to setpoint
		// Change setpoint by 5 degrees at most
		if (Roller.backRoller) {
			double backSetpoint = roller.backArm.getSetpoint();
			if (Math.abs(OI.oppGamepadAuto.getRightStickY()) > 0.1)
				backSetpoint += (OI.oppGamepadAuto.getRightStickY() * maxDegrees);
			
			// Make sure setpoint is within range
			if (backSetpoint > 90)
				backSetpoint = 90;
			else if (backSetpoint < 90 - Roller.potRange)
				backSetpoint = 90 - Roller.potRange;
			roller.backArm.setSetpoint(backSetpoint);
			
			// Determine the direction that the arm will be moving
			if (roller.backArm.getError() < 0)
				frontDirection = -backDirection;
			// If not within PID range
			if (roller.backPot.pidGet() > (roller.backArm.getSetpoint() + range) || roller.backPot.pidGet() < (roller.backArm.getSetpoint() - range)) {
				// Resets variables in PID and disables PID Controller
				if (roller.backArm.isEnable())
					roller.backArm.reset();
				// Drive motor at full speed in the correct direction
				roller.frontMotor.set(RobotMap.pickUpMaxSpeed * backDirection);
			} else { // If within range
				// Enable PID
				if (!roller.frontArm.isEnable())
					roller.frontArm.enable();
			}
			
			if (roller.frontArm.getSetpoint() < 85)
				roller.rollerFront.set(-1.0);
			else
				roller.rollerFront.set(0.0);
		}
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		
	}
	
}
