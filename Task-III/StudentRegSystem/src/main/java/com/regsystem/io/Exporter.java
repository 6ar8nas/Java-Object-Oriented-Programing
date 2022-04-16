package com.regsystem.io;

import java.util.List;

import com.regsystem.data.Student;

public interface Exporter {
	public void export(List<Student> students);
	
	public static String returnFilePath(String fileName) {
		return "com/regsystem/" + fileName; 
	}
}
