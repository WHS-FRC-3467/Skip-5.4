package org.usfirst.frc3467.subsystems.shooter.commands.auto;

import org.usfirst.frc3467.subsystems.SubsystemBase;

import edu.wpi.first.wpilibj.KinectStick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MyKinect extends Subsystem implements SubsystemBase {
	KinectStick rightArm;
	double armAngle = 0.0;
	
	public MyKinect() {
		rightArm = new KinectStick(2);
	}
	
	public void addButtons() {
		
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(new UpdateArmAngle());
	}
}
