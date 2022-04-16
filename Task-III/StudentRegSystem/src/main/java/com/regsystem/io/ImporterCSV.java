package com.regsystem.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.regsystem.data.DataSet;
import com.regsystem.data.Student;

public class ImporterCSV implements Importer {
	String fileName;
	
	public ImporterCSV(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public void fillData() throws IOException {
		BufferedReader reader = null;
		String line;
		try {
			reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().
					getResource(Importer.returnFilePath(fileName)).openConnection().getInputStream()));
			int linesRead = 0;
			while((line = reader.readLine()) != null) {
				if(linesRead == 0)
				{
					linesRead++;
					continue;
				}
				String[] row = line.split(",");
				String name = row[0];
				String surname = row[1];
				String programme = row[2];
				String yearStr = row[3];
				int year = Integer.parseInt("0"+yearStr);
				String groupStr = row[4];
				int group = Integer.parseInt("0"+groupStr);
				Student s = new Student(name, surname, programme, year, group);
				DataSet.getInstance().addStudent(s);
			}
			
		} catch (Exception e) {

		} finally {
			if(reader != null)
				reader.close();
		}
	}
}
