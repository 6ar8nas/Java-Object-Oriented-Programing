package com.regsystem.data;

import java.util.ArrayList;
import java.util.List;

public final class DataSet {
	
	private List<Student> students = new ArrayList<Student>();
	private final static DataSet INSTANCE = new DataSet();
	private int studentsCount = 0; 
	
	private DataSet() {}
	
	public static DataSet getInstance() {
		return INSTANCE;
	}
	
	public void addStudent(Student e) {
		students.add(e);
		++studentsCount;
	}
	
	public List<Student> getList() {
		return this.students;
	}
	
	public Student findInstanceByID(int id) {
		if(id > studentsCount)
			return null;
		for (Student student : students) {
			if (student.getId() == id) {
				return student;
			}
		}
		return null;
	}
	public void removeStudent(int id) {
		Student s = findInstanceByID(id);
		if(s == null)
			return;
		students.remove(s);
	}
}
