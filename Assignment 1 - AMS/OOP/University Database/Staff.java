package oop.University;

public class Staff extends Academic{

	String staffName;
	int staffId;
	String staffTitle;
	int hours;
	double tutor_payment = 40.0;
	double lecturer_payment = 80000;

	public Staff(String staffName, int staffId, Title staffTitle) {
		super(staffName, staffId, staffTitle);

		this.staffName = staffName;
		this.staffId = staffId;
		this.staffTitle = String.valueOf(staffTitle);
	}

	public void setHours(int hours) {
		this.hours = hours;
	}



	@Override
	public double getWeeklyPay() {

		if(staffTitle == String.valueOf(Title.TUTOR)) {
			this.tutor_payment *= hours;
			return tutor_payment;
		} else {
			return lecturer_payment/52;
		}
	}

	@Override
	public String toString() {
		String staffdeets;
		staffdeets = "Staff " + getID() + " works as a " +
				this.getTitle().toString();

		return staffdeets;
	}



}
