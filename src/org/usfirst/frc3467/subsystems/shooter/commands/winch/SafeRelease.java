package org.usfirst.frc3467.subsystems.shooter.commands.winch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class SafeRelease extends CommandGroup {
	public SafeRelease() {
		this.addSequential(new EngageMotor());
		this.addSequential(new WaitCommand(0.3));
		this.addSequential(new UnlockBrake());
	}
}
