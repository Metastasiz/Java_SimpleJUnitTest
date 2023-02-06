package eu.glowacki.utp.assignment03.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static java.time.temporal.ChronoUnit.*;

public class Trainee extends Employee {

	// (assignment 02)
	// attributes:
	// * practice start date
	// * practice length (in days)
	
	// (assignment 03)
	// * practice length is shorter than given number of days
	// * practice length is longer than given number of days
    private Date start;
    private int length;

    public Trainee(String firstName, String lastName, Date dob, BigDecimal sal, int length, Date start, Manager m) {
        super(firstName, lastName, dob, sal, m);
        this.start = start;
        this.length = length;
    }

    public Date getStartDate(){return start;}
    public int getLength(){return length;}
    public int getDurationEmDay() {
        LocalDate date = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        return (int)DAYS.between(date, today);
    }
    public int getDurationEmMonth() {
        LocalDate date = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        return (int)MONTHS.between(date, today);
    }
    public int getDurationEmYear() {
        LocalDate date = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        return (int)YEARS.between(date, today);
    }
    //
    public boolean emShorter(int day) throws IndexOutOfBoundsException{
        if (day < 0) throw new IndexOutOfBoundsException("wat u input tho");
        return getDurationEmDay()<day;
    }
    public boolean emLonger(int day) throws IndexOutOfBoundsException {
        if (day < 0) throw new IndexOutOfBoundsException("wat u input tho");
        return getDurationEmDay()>day;
    }
}