package org.usfirst.frc3467.subsystems.shooter.commands.winch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class EndWinch extends CommandGroup {
	public EndWinch() {
		this.addSequential(new LockBrake());
		this.addSequential(new WaitCommand(0.03));
		this.addSequential(new SetMotor(0.0));
		this.addSequential(new DisengageMotor());
	}
}
