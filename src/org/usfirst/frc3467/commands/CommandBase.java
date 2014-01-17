package org.usfirst.frc3467.commands;

import java.util.Vector;

import org.usfirst.frc3467.OI;
import org.usfirst.frc3467.subsystems.SubsystemBase;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	public static OI oi;
	public static CommandBase cb;
	public static org.usfirst.frc3467.subsystems.DriveBase3.DriveBase db;
	
	public static Vector subsystemList;
	
	public static void init() {
		oi = new OI();
		subsystemList = new Vector();
		// Add new subsystems to the list
		// db = new DriveBase();
		
		db = new org.usfirst.frc3467.subsystems.DriveBase3.DriveBase();
		subsystemList.addElement(db);
		
		for (int i = 0; i < subsystemList.size(); i++) {
			((SubsystemBase) subsystemList.elementAt(i)).addButtons();
			printSys((SubsystemBase) subsystemList.elementAt(i));
		}
		
	}
	
	private static void printSys(SubsystemBase subsystem) {
		System.out.println("Creating: " + subsystem.getClass().getName() + "    Version: ");
	}
	
	public CommandBase() {
		super();
		cb = this;
		
	}
	
	public CommandBase(String name) {
		super(name);
	}
	
}
