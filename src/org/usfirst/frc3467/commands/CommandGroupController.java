package org.usfirst.frc3467.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public abstract class CommandGroupController extends CommandGroup {
	public boolean cancel = true;
	
	public abstract void cancel();
	
}
