package org.usfirst.frc3467;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	
	public static Joystick leftJoystick;
	public static Joystick rightJoystick;
	
	public OI() {
		leftJoystick = new Joystick(2);
		rightJoystick = new Joystick(1);
	}
}
