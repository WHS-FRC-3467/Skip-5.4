package org.usfirst.frc3467.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto extends CommandGroup {
	public Auto() {
		// Pick an autonomous routine to run
		this.addSequential(new DriveForward(), 2);
		// this.addSequential(new SetRollerFrontPoint(45), 2);
		// this.addSequential(new WinchIn(), 3);
		// this.addSequential(new SetRollerFrontPoint(90), 2);
	}
}
