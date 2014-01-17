package org.usfirst.frc3467.subsystems.DriveBase3;

import org.usfirst.frc3467.RobotMap;
import org.usfirst.frc3467.interfaces.Output;
import org.usfirst.frc3467.subsystems.SubsystemBase;
import org.usfirst.frc3467.subsystems.DriveBase3.commands.Drive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase extends Subsystem implements SubsystemBase {
	
	private static final double SUBSYSTEM_VERSION = 1.0;
	
	private Talon left1;
	private Talon left2;
	private Talon right1;
	private Talon right2;
	
	private RobotDrive tankDrive;
	private PIDController leftSide;
	
	private Encoder rightSideEnc;
	private Encoder leftSideEnc;
	
	private Output rightPidOutput;
	private Output leftPidOutput;
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(new Drive());
	}
	
	public DriveBase() {
		System.out.print("Creating: Drivebase1");
		
		right1 = RobotMap.talon1;
		// right2 = RobotMap.talon2;
		left1 = RobotMap.talon2;
		// left2 = RobotMap.talon4;
		
		rightSideEnc = new Encoder(11, 12);
		rightSideEnc.start();
		leftSideEnc = new Encoder(13, 14);
		leftSideEnc.start();
		
		rightPidOutput = new Output();
		leftPidOutput = new Output();
		
		leftSide = new PIDController(0.0, 0.0, 0.0, leftSideEnc, leftPidOutput);
		
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
		tankDrive.tankDrive(-left, -right);
		SmartDashboard.putNumber("Left Encoder", leftSideEnc.getRaw() / 1000);
		SmartDashboard.putNumber("Right Encoder", rightSideEnc.getRaw() / 1000);
	}
}
