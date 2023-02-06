package eu.glowacki.utp.assignment03.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static java.time.temporal.ChronoUnit.*;

public class Worker extends Employee {

	// (assignment 02)
	// attributes:
	// * employment date
	// * bonus
	
	// (assignment 03)
	// attributes:
	// * has bonus
	//
	// methods:
	// * seniority is longer than given number of years (seniority - sta?)
	// * seniority is longer than given number of months
	// * has bonus greater than given amount of money
    private BigDecimal bonus;
    private Date start;

    public Worker(String firstName, String lastName, Date dob, BigDecimal sal, BigDecimal bonus, Date start, Manager m) {
        super(firstName, lastName, dob, sal, m);
        this.bonus = bonus;
        this.start = start;
    }

    public BigDecimal getBonus(){return bonus;}
    public Date getStartDate(){return start;}
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
    public boolean hasBonus(){
        return bonus != null && bonus.intValue() > 0;
    }
    public boolean moreThanYear(double year) throws Exception {
        if (year < 0) throw new Exception("wat u input tho");
        LocalDate date = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        return (double)YEARS.between(date, today) > year;
    }
    public boolean moreThanMonth(double month) throws Exception {
        if (month < 0) throw new Exception("wat u input tho");
        LocalDate date = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        return (double)MONTHS.between(date, today) > month;
    }
    public boolean moreThanBonus(BigDecimal a) throws Exception {
        if (a == null || a.intValue() < 0) throw new Exception("wat u input tho");
        else if (hasBonus()) return false;
        return bonus.intValue() > a.intValue();
    }
    //
    public void setBonus(BigDecimal a){
        bonus = a;
    }
}