package org.usfirst.frc3467.interfaces;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class Output implements PIDOutput, LiveWindowSendable {
	
	private double output = -2000;
	
	public void pidWrite(double output) {
		this.output = output;
		System.out.println("Setting output to: " + output);
	}
	
	public double getOutput() {
		return output;
	}
	
	ITable table;
	
	public void initTable(ITable subtable) {
		table = subtable;
		updateTable();
	}
	
	public ITable getTable() {
		
		return table;
	}
	
	public String getSmartDashboardType() {
		return "PID Output";
	}
	
	public void updateTable() {
		if (table != null) {
			table.putNumber("Output", output);
		}
	}
	
	public void startLiveWindowMode() {
		
	}
	
	public void stopLiveWindowMode() {
		
	}
	
}
