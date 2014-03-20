package org.usfirst.frc3467.other.ultrasonics;

import java.util.TimerTask;

import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * @author team3467
 */

public class MaxbotixI2C extends Subsystem {
	public static final int ULTRASONIC_ADDRESS1 = 0xE0;
	public static final int ULTRASONIC_ADDRESS2 = 0xE1;
	
	private static final int ULTRASONIC_SLOT = DigitalModule.getDefaultDigitalModule();
	private static final int ECHO_ADDRESS_HIGH = 2;
	private static final int ECHO_ADDRESS_LOW = 3;
	
	private I2C i2c;
	
	private Timer timer;
	private java.util.Timer controlLoop;
	
	private int distance = 0;
	// private int curr_dist = 0;
	private double period = 0.05;
	private boolean writeFail = false;
	
	private class UltrasonicTask extends TimerTask {
		private MaxbotixI2C u;
		
		public UltrasonicTask(MaxbotixI2C ultrasonic) {
			if (ultrasonic == null) {
				throw new NullPointerException("Given Ultrasonic was null");
			}
			this.u = ultrasonic;
		}
		
		public void run() {
			u.setDist();
		}
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new UltrasonicDoNothing());
	}
	
	public MaxbotixI2C(int address) {
		if (address == 0x0)
			address = ULTRASONIC_ADDRESS1;
		
		DigitalModule digiMod = DigitalModule.getInstance(ULTRASONIC_SLOT);
		i2c = digiMod.getI2C(address);
		
		timer = new Timer();
		controlLoop = new java.util.Timer();
		
		if (i2c.addressOnly()) {
			LogDebugger.log("ERROR: Could not find Ultrasonic.");
		} else {
			setDist();
		}
		
	}
	
	/**
	 * Ends the control loop.
	 */
	public void free() {
		controlLoop.cancel();
		controlLoop = null;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public boolean getWriteFail() {
		return writeFail;
	}
	
	boolean flop = false;
	boolean flop2 = false;
	
	// gets distance
	private void setDist() {
		byte high, low;
		
		try {
			// setting range to centimeters
			// if (i2c.write(0x00, 0x51)) {
			byte[] buffer1 = new byte[1];
			buffer1[0] = 0x51;
			if (flop)
				if (i2c.transaction(buffer1, 1, null, 0)) {
					writeFail = true;
					LogDebugger.log("Attempted write to ultrasonic, failed.");
					System.out.println("Failed to write");
				} else {
					System.out.println("Wrote");
					flop = false;
				}
			if (flop2) {
				flop2 = false;
				writeFail = false;
				LogDebugger.log("Wrote to ultrasonic, began rangefinding in centimeters.");
				timer.reset();
				timer.start();
				
				try {
					boolean done = false;
					Timer.delay(.065);
					for (int i = 0; i < 10; i++) {
						byte[] buffer = new byte[2];
						// if (i2c.read(ECHO_ADDRESS_HIGH, 1, buffer)) {
						if (i2c.transaction(null, 0, buffer, 2)) {
							// LogDebugger.log(Debug.ULTRASONIC, "Attempted read to ultrasonic high, failed.");
							System.out.println("Failed to read");
						} else {
							System.out.println("Read");
							done = true;
							
							high = buffer[0];
							low = buffer[1];
							distance = ((int) high & 0xFF) * 256 + ((int) low & 0xFF);
							LogDebugger.log("Try: " + i + " Time: " + timer.get() + " High: " + high + " Low: " + low + " Dist: " + distance);
							
						}
						if (done)
							break;
						
						// LogDebugger.log("Try "+i+" failed.");
						if (i == 9)
							LogDebugger.log("TRIED 10 TIMES!");
						Timer.delay(.02);
					}
				} catch (Exception e) {
					LogDebugger.log("Attempt to read ultrasonic caused a fatal exception.");
				}
			}
		} catch (Exception e) {
			LogDebugger.log("Attempt to write to ultrasonic caused a fatal exception");
		}
		
		// Schedule next loop.
		controlLoop.schedule(new UltrasonicTask(this), (long) (period * 1000));
	}
	
}
