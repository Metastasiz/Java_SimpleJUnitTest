package eu.glowacki.utp.assignment02.employee;

import java.math.BigDecimal;
import java.util.Date;

public abstract class Employee extends Person {

	//
	// attributes:
	// * salary (use BigDecimal type for representing currency values)
	// * manager (empty if at top of hierarchy)
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

	public abstract int getDuration();
}