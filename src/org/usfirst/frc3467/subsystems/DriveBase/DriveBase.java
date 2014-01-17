package org.usfirst.frc3467.subsystems.DriveBase;

import org.usfirst.frc3467.RobotMap;
import org.usfirst.frc3467.subsystems.SubsystemBase;
import org.usfirst.frc3467.subsystems.DriveBase.commands.Drive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem implements SubsystemBase {
	
	private static final double SUBSYSTEM_VERSION = 1.0;
	
	private Talon left1;
	private Talon left2;
	private Talon right1;
	private Talon right2;
	private RobotDrive tankDrive;
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(new Drive());
	}
	
	public DriveBase() {
		System.out.print("Creating: Drivebase1");
		
		right1 = RobotMap.talon1;
		right2 = RobotMap.talon2;
		left1 = RobotMap.talon3;
		left2 = RobotMap.talon4;
		
		tankDrive = new RobotDrive(left1, left2, right1, right2);
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
