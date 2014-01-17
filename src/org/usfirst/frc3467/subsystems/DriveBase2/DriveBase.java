package org.usfirst.frc3467.subsystems.DriveBase2;

import org.usfirst.frc3467.RobotMap;
import org.usfirst.frc3467.subsystems.SubsystemBase;
import org.usfirst.frc3467.subsystems.DriveBase2.commands.Drive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem implements SubsystemBase {
	
	private static final double SUBSYSTEM_VERSION = 1.0;
	
	private Talon left1;
	private Talon right1;
	private RobotDrive tankDrive;
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(new Drive());
	}
	
	public DriveBase() {
		System.out.print("Creating: Drivebase2");
		
		right1 = RobotMap.talon1;
		left1 = RobotMap.talon2;
		
		tankDrive = new RobotDrive(left1, right1);
	}
	
	public void addButtons() {
		// Add your buttons here
		// Button testButton = new JoystickButton(OI.joystick1, 1); // Creates a button on the joystick trigger
		// testButton.whenPressed(new ExampleCommand());
	}
	
	public double getVersion() {
		return SUBSYSTEM_VERSION;
	}
	
	public void drive(double left, double right) {
		tankDrive.tankDrive(left, right);
	}
}
