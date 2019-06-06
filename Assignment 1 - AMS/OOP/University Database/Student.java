package oop.University;

public class Student extends Academic {

	String studentName;
	int studentId;
	String studentTitle;
	double stipend;

	public Student(String studentName, int studentId, Title studentTitle, double stipend) {
		super(studentName, studentId, studentTitle);

		this.stipend = stipend;
		this.studentName = studentName;
		this.studentId = studentId;
		this.studentTitle = String.valueOf(studentTitle);
	}



	@Override
	public double getWeeklyPay() {
		return stipend;
	}

	@Override
	public String toString() {
		String hi;
		hi = "Student " + getID() + " studies a " +
				this.getTitle().toString(); //gets the title from Academic
											//and converts it to string
											//using toString() from Title.java
		return hi;
	}



}
