package com.regsystem.io;

import java.util.List;

import com.regsystem.data.Student;

public class ExporterPDF implements Exporter {
	String fileName;
	
	public ExporterPDF(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public void export(List<Student> students) {
		// TODO Auto-generated method stub
		
	}

}
