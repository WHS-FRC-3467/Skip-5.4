package org.usfirst.frc3467.other;

import org.usfirst.frc3467.CommandBasedRobot;
import org.usfirst.frc3467.commands.CommandBase;

import edu.wpi.first.wpilibj.PIDController;

public class PotCalibration extends CommandBase {
	int calibrationTime = 10;
	
	public PotCalibration() {
		this.setTimeout(calibrationTime);
	}
	
	protected void initialize() {
		System.out.println("Starting Pot Calibration");
		System.out.println("Disabling all PID loops");
		for (int i = 0; i < CommandBasedRobot.PIDList.size(); i++) {
			PIDController controller = (PIDController) CommandBasedRobot.PIDList.elementAt(i);
			controller.disable();
		}
		System.out.println("Calibrating Pots in 10 seconds");
	}
	
	int count = 0;
	
	protected void execute() {
		count++;
		if (count % 20 == 0)
			System.out.println("Time to calibration = " + (calibrationTime - this.timeSinceInitialized()) + " seconds");
	}
	
	protected boolean isFinished() {
		return this.isTimedOut();
	}
	
	protected void end() {
		CommandBasedRobot.mast.appendToFile(Double.toString(shooter.pot.get()));
		CommandBasedRobot.winch.appendToFile(Double.toString(winch.pot.get()));
		CommandBasedRobot.fr.appendToFile(Double.toString(roller.frontPot.get()));
		CommandBasedRobot.br.appendToFile(Double.toString(roller.backPot.get()));
		potInit();
		System.out.println("Calibrated. Renabling all PID loops");
		for (int i = 0; i < CommandBasedRobot.PIDList.size(); i++) {
			PIDController controller = (PIDController) CommandBasedRobot.PIDList.elementAt(i);
			controller.reset();
			controller.enable();
		}
	}
	
	protected void interrupted() {
		end();
	}
}
