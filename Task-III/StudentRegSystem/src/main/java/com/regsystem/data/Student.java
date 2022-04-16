package com.regsystem.data;

public class Student {
private static int lastId = 0;
	
	private int id;
	private String name;
	private String surname;
	private int year;
	private int group;
	private String studyProgramme;
	private DateEntry[] attendenceValues;
	
	public Student(String name, String surname, String studyProgramme, int year, int group) {
		this.id = nextId();
		this.name = name;
		this.surname = surname;
		this.year = year;
		this.group = group;
		this.studyProgramme = studyProgramme;
	}
	
	private static int nextId() {
		return ++lastId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public int getId() {
		return id;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getStudyProgramme() {
		return studyProgramme;
	}
	
	public int getGroup() {
		return group;
	}
	
	public DateEntry[] getAttendenceValues() {
		return attendenceValues;
	}
	
	public void setName(String value) {
		name = value;
	}
	
	public void setSurname(String value) {
		surname = value;
	}
	
	public void setYear(int value) {
		year = value;
	}
	
	public void setStudyProgramme(String value) {
		studyProgramme = value;
	}
	
	public void setGroup(int value) {
		group = value;
	}
	
	public void setAttendenceValues() {
		return ; // TODO
	}
}
