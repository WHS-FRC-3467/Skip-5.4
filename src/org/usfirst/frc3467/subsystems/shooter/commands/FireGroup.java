package org.usfirst.frc3467.subsystems.shooter.commands;

import org.usfirst.frc3467.subsystems.shooter.commands.winch.WinchIn;
import org.usfirst.frc3467.subsystems.shooter.commands.winch.groups.EndWinch;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FireGroup extends CommandGroup {
	public FireGroup() {
		this.addSequential(new Fire(0.0, true));
		this.addSequential(new WinchIn());
		this.addSequential(new EndWinch());
	}
}
