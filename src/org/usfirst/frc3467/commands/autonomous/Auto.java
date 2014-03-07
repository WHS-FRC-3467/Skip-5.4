package org.usfirst.frc3467.commands.autonomous;

import org.usfirst.frc3467.subsystems.DriveBase3V3.commands.DriveStraight;
import org.usfirst.frc3467.subsystems.DriveBase3V3.commands.ShiftUp;
import org.usfirst.frc3467.subsystems.shooter.commands.FireGroup;
import org.usfirst.frc3467.subsystems.shooter.commands.SetSetpoint;
import org.usfirst.frc3467.subsystems.shooter.commands.winch.WinchIn;
import org.usfirst.frc3467.subsystems.shooter.commands.winch.groups.EndWinch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Auto extends CommandGroup {
	public Auto() {
		// Pick an autonomous routine to run
		this.addParallel(new ShiftUp());
		this.addSequential(new WinchIn());
		this.addSequential(new EndWinch());
		this.addSequential(new DriveStraight(112));
		this.addSequential(new SetSetpoint(115));
		this.addSequential(new WaitCommand(1.0));
		this.addSequential(new FireGroup());
		this.addSequential(new SetSetpoint(90));
	}
}
