package org.usfirst.frc3467.commands.autonomous;

import org.usfirst.frc3467.subsystems.DriveBase3V3.commands.DriveStraight;
import org.usfirst.frc3467.subsystems.DriveBase3V3.commands.ShiftUp;
import org.usfirst.frc3467.subsystems.shooter.commands.NewSoftShot;
import org.usfirst.frc3467.subsystems.shooter.commands.SetSetpoint;
import org.usfirst.frc3467.subsystems.shooter.commands.winch.WinchIn;
import org.usfirst.frc3467.subsystems.shooter.commands.winch.groups.EndWinch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoShort extends CommandGroup {
	public AutoShort() {
		// Pick an autonomous routine to run
		this.addParallel(new ShiftUp());
		this.addSequential(new WinchIn());
		this.addSequential(new EndWinch());
		this.addSequential(new DriveStraight(168));
		this.addSequential(new SetSetpoint(115, false));
		this.addSequential(new WaitCommand(1.0));
		this.addSequential(new NewSoftShot(1.0));
		this.addSequential(new SetSetpoint(90, false));
	}
}
