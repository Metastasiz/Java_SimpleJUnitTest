package eu.glowacki.utp.assignment03.employee;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.YEARS;

public abstract class Person {

	// (assignment 02)
	// attributes:
	// * first name
	// * surname
	// * birth date
	// * age (derived --- computed based on birth date)
	
	// (assignment 03)
	// methods:
	// * is older than other person
	// * is younger than other person
	// * compare age with other person's age
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
    //
    public int getDurationDay() {
        LocalDate date = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        return (int)DAYS.between(date, today);
    }
    public int compareAge2(Person p) throws Exception {
        if (p == null) throw new Exception("wat u input tho");
        return getAge() - p.getAge();
    }
    public int compareAge(Person p) {
        return p.getDob().compareTo(getDob());
    }
    public boolean isOlder(Person p) {
        return compareAge(p)>=1;
    }

    public boolean isYounger(Person p) throws Exception {
        if (p == null) throw new Exception("wat u input tho");
        return compareAge(p)<=-1;
    }
    public boolean isOlderYear(int year) throws IndexOutOfBoundsException {
        if (year < 0) throw new IndexOutOfBoundsException("wat u input tho");
        LocalDate date = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        return (int)YEARS.between(date, today) > year;
    }
}