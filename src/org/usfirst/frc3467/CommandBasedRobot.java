/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc3467;

import java.util.Vector;

import org.usfirst.frc3467.commands.CommandBase;
import org.usfirst.frc3467.commands.autonomous.Auto;
import org.usfirst.frc3467.commands.autonomous.Auto2Back;
import org.usfirst.frc3467.commands.autonomous.Auto2Front;
import org.usfirst.frc3467.commands.autonomous.AutoShort;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
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
	public static Vector PIDList;
	
	public CommandBasedRobot() {
		PIDList = new Vector();
	}
	
	public void robotInit() {
		// Initialize all subsystems
		CommandBase.init();
		// Start compressor
		compressor = new Compressor(RobotMap.compressorPressureSwitch, RobotMap.comperessorSpike);
		compressor.start();
		
		// Add autonomous selector
		autoChooser = new SendableChooser();
		autoChooser.addDefault("1 Ball Normal", new Auto());
		autoChooser.addDefault("1 Ball Close", new AutoShort());
		autoChooser.addObject("2 Ball Back", new Auto2Back());
		autoChooser.addObject("2 Ball Front", new Auto2Front());
		SmartDashboard.putData("Auto", autoChooser);
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
		
		// Resets all integral terms in pid controllers
		for (int i = 0; i < PIDList.size(); i++) {
			PIDController controller = (PIDController) PIDList.elementAt(i);
			controller.reset();
			controller.enable();
		}
		
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
