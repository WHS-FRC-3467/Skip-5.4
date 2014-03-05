package org.usfirst.frc3467.subsystems.shooter.commands;

import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.subsystems.shooter.commands.winch.WinchIn;
import org.usfirst.frc3467.subsystems.shooter.commands.winch.groups.EndWinch;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NewSoftShot extends CommandGroup {
	public boolean done = false;
	
	public NewSoftShot() {
		requires(CommandBase.winch);
		this.addSequential(new Fire(1.0, false));
		this.addSequential(new WinchIn());
		this.addSequential(new EndWinch());
	}
}
