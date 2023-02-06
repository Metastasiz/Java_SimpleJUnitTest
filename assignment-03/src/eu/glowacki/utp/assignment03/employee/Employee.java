package eu.glowacki.utp.assignment03.employee;

import java.math.BigDecimal;
import java.util.Date;

public abstract class Employee extends Person {

	// (assignment 02)
	// attributes:
	// * salary
	// * manager (empty if at top of hierarchy)

	// (assignment 03)
	// methods:
	// * salary is greater than given amount of money
	// * salary is less than given amount of money
	// * compare salary with other employee salary
    private BigDecimal salary;
    private Manager manager;

    protected Employee(String firstName, String lastName, Date dob, BigDecimal sal, Manager m) {
        super(firstName, lastName, dob);
        salary = sal;
        manager = m;
        if (m != null){
            m.addSub(this);
        }
    }
    public void setManager(Manager m){
        manager = m;
    }
    public BigDecimal getSalary(){ return salary; }

    public Manager getManager() {
        return manager;
    }

    public abstract int getDurationEmDay();
    public abstract int getDurationEmMonth();
    public abstract int getDurationEmYear();
    //
    public BigDecimal compareSal2(Employee a) throws Exception {
        if (a == null) throw new Exception("wat u input tho");
        return salary.subtract(a.getSalary());
    }
    public int compareSal(Employee a) throws Exception {
        if (a == null) throw new Exception("wat u input tho");
        return salary.compareTo(a.getSalary());
    }
    public boolean earnMore(BigDecimal a) throws Exception {
        if (a == null || a.intValue() < 0) throw new Exception("wat u input tho");
        return salary.compareTo(a)>=1;
    }
    public boolean earnLess(BigDecimal a) throws Exception {
        if (a == null || a.intValue() < 0) throw new Exception("wat u input tho");
        return salary.compareTo(a)<=-1;
    }
    //
    public void setSalaryPercent(double a){
        salary = new BigDecimal(salary.doubleValue()*a);
    }
    public void setSalary(BigDecimal a){
        salary = a;
    }

}