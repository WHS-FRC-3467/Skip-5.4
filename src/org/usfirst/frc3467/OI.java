package org.usfirst.frc3467;

import org.usfirst.frc3467.control.Gamepad;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class OI {
	
	public static Joystick leftJoystick;
	public static Joystick rightJoystick;
	public static Gamepad oppGamepadAuto;
	public static Joystick oppGamepadManu;
	
	// DriveBase
	Command DriveStraight;
	Command DriveTurn;
	Command ResetDBSensors;
	Command ShiftDown;
	Command ShiftUp;
	
	// Roller
	
	public OI() {
		leftJoystick = new Joystick(1);
		rightJoystick = new Joystick(2);
		oppGamepadAuto = new Gamepad(3);
		oppGamepadManu = new Joystick(4);
	}
	
}
