package com.regsystem.io;

public interface IODevice {
	public static String returnFilePath(String fileName) {
		return "io/" + fileName; 
	}
}
