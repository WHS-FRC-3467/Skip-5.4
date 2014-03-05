package org.usfirst.frc3467.subsystems.rollers;

import org.usfirst.frc3467.RobotMap;
import org.usfirst.frc3467.subsystems.SubsystemBase;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Rollies extends Subsystem implements SubsystemBase {
	
	public Talon rollerFront;
	public Talon rollerBack;
	
	public void addButtons() {
		
	}
	
	protected void initDefaultCommand() {
		
	}
	
	public Rollies() {
		rollerFront = new Talon(RobotMap.rollerTalonFront);
		rollerBack = new Talon(RobotMap.rollerTalonBack);
	}
	
}
