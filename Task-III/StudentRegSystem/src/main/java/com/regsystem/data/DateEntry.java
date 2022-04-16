package com.regsystem.data;

public class DateEntry {
	private int date;
	private boolean attendance;
	
	public DateEntry(int date) {
		this.date = date;
		this.attendance = true;
	}
	
	public int getDate() {
		return date;
	}
	
	public boolean getAttendance() {
		return attendance;
	}
	
	public void setAttendence(boolean value) {
		this.attendance = value;
	}
}
