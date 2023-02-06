package eu.glowacki.utp.assignment02.employee;

import java.util.Calendar;
import java.util.Date;

public abstract class Person {

	// To implement an attribute means that you provide a backing field and
	// getter or optionally setter for read-write properties/attributes
	// 
	// NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
	// THOSE SHOULD BE COMPUTED ON-LINE
	//
	// attributes:
	// * first name (read-only)
	// * surname (read-only)
	// * birth date (read-only) --- date MUST BE represented by an instance of
	// the type designed for date representation (either Date or LocalDate)
	//
	// * age (derived --- computed based on birth date) --- implemented as a
	// getter calculating the difference between the current date and birth date

	private final String _firstName, _lastName;// backing field
	private final Date dob;

	protected Person(String firstName, String lastName, Date dob) {
		_firstName = firstName;
		_lastName = lastName;
		this.dob = dob;
	}

	public String getFirstName() {return _firstName;}
	public String getLastName() {return _lastName;}
	public Date getDob() {return dob;}
	
	public int getAge() {
		Date current = new Date();
		Calendar c = Calendar.getInstance();

		c.setTime(current);
		int curY = c.get(Calendar.YEAR);

		c.setTime(dob);
		int bthY = c.get(Calendar.YEAR);

		return curY - bthY;
	}
}