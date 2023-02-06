package eu.glowacki.utp.assignment02.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class Trainee extends Employee {

	// attributes:
	// * apprenticeship start date
	// * apprenticeship length (in days)

	private Date start;
	private int length;

	public Trainee(String firstName, String lastName, Date dob, BigDecimal sal, int length, Date start, Manager m) {
		super(firstName, lastName, dob, sal, m);
		this.start = start;
		this.length = length;
	}

	public Date getStartDate(){return start;}
	public int getLength(){return length;}
	public int getDuration() {
		LocalDate date = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate today = LocalDate.now();
		return (int)DAYS.between(date, today);
	}
}