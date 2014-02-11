package org.usfirst.frc3467.subsystems.DriveBase3V3.commands;

import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.interfaces.Output;
import org.usfirst.frc3467.pid.Pivot;

import edu.wpi.first.wpilibj.PIDController;

public class DriveTurn extends CommandBase {
	
	int degrees = 0;
	
	final double distanceBetweenWheels = 24; // In inches
	
	PIDController distanceController;
	private final double Kp = 0.0;
	private final double Ki = 0.0;
	private final double Kd = 0.0;
	private Output pidOutput;
	private int pivotPoint;
	
	public DriveTurn(int degrees, int pivotPoint) {
		requires(db);
		this.setInterruptible(true);
		this.degrees = degrees;
		// pidOutput = new Output();
		this.pivotPoint = pivotPoint;
		switch (pivotPoint) {
			case Pivot.LEFT:
				distanceController = new PIDController(Kp, Ki, Kd, db.leftSideEnc, pidOutput);
				break;
			case Pivot.RIGHT:
				distanceController = new PIDController(Kp, Ki, Kd, db.rightSideEnc, pidOutput);
				break;
			case Pivot.CENTER:
				// distanceController = new PIDController(Kp, Ki, Kd, db.leftSideEnc, pidOutput);
				break;
		}
		//
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		
	}
	
	protected void interrupted() {
		end();
	}
	
}
