package org.usfirst.frc3467.commands;

import java.util.Vector;

import org.usfirst.frc3467.OI;
import org.usfirst.frc3467.subsystems.DriveBase3V3.DriveBase;
import org.usfirst.frc3467.subsystems.rollers.Roller;
import org.usfirst.frc3467.subsystems.rollers.Rollies;
import org.usfirst.frc3467.subsystems.shooter.Shooter;
import org.usfirst.frc3467.subsystems.shooter.Winch;
import org.usfirst.frc3467.subsystems.shooter.commands.auto.MyKinect;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	public static OI oi;
	public static CommandBase cb;
	public static DriveBase db;
	public static Shooter shooter;
	public static Winch winch;
	public static Roller roller;
	public static Rollies rollies;
	public static MyKinect kinect;
	
	public static Vector subsystemList;
	
	public static void init() {
		oi = new OI();
		subsystemList = new Vector();
		// Add new subsystems to the list
		
		db = new DriveBase();
		subsystemList.addElement(db);
		
		shooter = new Shooter();
		subsystemList.addElement(shooter);
		
		roller = new Roller();
		subsystemList.addElement(roller);
		
		rollies = new Rollies();
		subsystemList.addElement(rollies);
		
		// Didn't add to PIDList
		winch = new Winch();
		subsystemList.addElement(winch);
		
		kinect = new MyKinect();
		subsystemList.addElement(kinect);
		
		oi.BindCommands(OI.JAKE);
		oi.BindCommands(OI.JAMES);
	}
	
	public CommandBase() {
		super();
		cb = this;
		
	}
	
	public CommandBase(String name) {
		super(name);
	}
	
}
