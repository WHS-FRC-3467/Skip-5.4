package org.usfirst.frc3467.commands.autonomous;

import org.usfirst.frc3467.subsystems.DriveBase3V3.commands.DriveStraight;
import org.usfirst.frc3467.subsystems.DriveBase3V3.commands.ShiftUp;
import org.usfirst.frc3467.subsystems.rollers.commands.SetRoller;
import org.usfirst.frc3467.subsystems.shooter.commands.FireGroup;
import org.usfirst.frc3467.subsystems.shooter.commands.SetSetpoint;
import org.usfirst.frc3467.subsystems.shooter.commands.winch.WinchIn;
import org.usfirst.frc3467.subsystems.shooter.commands.winch.groups.EndWinch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Auto2Back extends CommandGroup {
	public Auto2Back() {
		// Pick an autonomous routine to run
		
		// Shift into high gear
		this.addParallel(new ShiftUp());
		
		// Winch In
		this.addSequential(new WinchIn());
		this.addSequential(new EndWinch());
		
		// Grab second ball
		this.addSequential(new SetRoller(1.0, false), 0.3);
		
		// Drive forth!
		this.addSequential(new DriveStraight(124));
		
		// Shoot!
		this.addParallel(new SetRoller(-1.0, false), 0.2);
		this.addSequential(new SetSetpoint(120));
		this.addSequential(new WaitCommand(1.0));
		this.addSequential(new FireGroup());
		this.addSequential(new SetSetpoint(60));
		this.addSequential(new SetRoller(1.0, false), 0.6);
		this.addSequential(new SetSetpoint(120));
		this.addSequential(new WaitCommand(1.0));
		this.addSequential(new FireGroup());
		
		// Restore all limbs
		this.addSequential(new SetSetpoint(90));
	}
}
