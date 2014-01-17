package org.usfirst.frc3467;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	
	public static Joystick joystick1;
	public static Joystick joystick2;
	
	public OI() {
		joystick1 = new Joystick(2);
		joystick2 = new Joystick(1);
	}
}
