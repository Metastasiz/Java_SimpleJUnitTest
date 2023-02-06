package eu.glowacki.utp.assignment02.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class Worker extends Employee {

	// attributes
	// * employment date
	// * bonus

	private BigDecimal bonus;
	private Date start;

	public Worker(String firstName, String lastName, Date dob, BigDecimal sal, BigDecimal bonus, Date start, Manager m) {
		super(firstName, lastName, dob, sal, m);
		this.bonus = bonus;
		this.start = start;
	}

	public BigDecimal getBonus(){return bonus;}
	public Date getStartDate(){return start;}
	public int getDuration() {
		LocalDate date = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate today = LocalDate.now();
		return (int)DAYS.between(date, today);
	}
}