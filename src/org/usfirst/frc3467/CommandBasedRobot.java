/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3467;

import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.commands.autonomous.Auto;
import org.usfirst.frc3467.commands.autonomous.Auto2Back;
import org.usfirst.frc3467.commands.autonomous.Auto2Front;
import org.usfirst.frc3467.subsystems.shooter.Shooter;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the IterativeRobot documentation. If you change the name of this class or the package after creating this project, you must also update the manifest file in the resource directory.
 */
public class CommandBasedRobot extends IterativeRobot {
	Compressor compressor;
	Command autonomousCommand;
	SendableChooser autoChooser;
	
	public void robotInit() {
		// instantiate the command used for the autonomous period
		System.out.println("Version 0.02");
		// Initialize all subsystems
		CommandBase.init();
		compressor = new Compressor(RobotMap.compressorPressureSwitch, RobotMap.comperessorSpike);
		compressor.start();
		
		autoChooser = new SendableChooser();
		autoChooser.addDefault("1 Ball", new Auto());
		autoChooser.addObject("2 Ball Back", new Auto2Back());
		autoChooser.addObject("2 Ball Front", new Auto2Front());
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);
	}
	
	public void autonomousInit() {
		// schedule the autonomous command (example)
		autonomousCommand = (Command) autoChooser.getSelected();
		autonomousCommand.start();
	}
	
	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		Shooter.getInstance().arm.reset();
		Shooter.getInstance().arm.enable();
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
	
}
