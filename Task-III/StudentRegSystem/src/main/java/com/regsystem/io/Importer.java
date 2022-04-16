package com.regsystem.io;

import java.io.IOException;

public interface Importer {
	public void fillData() throws IOException;
	
	public static String returnFilePath(String fileName) {
		return "com/regsystem/" + fileName; 
	}
}
