package eu.glowacki.utp.assignment02.payroll;

import java.math.BigDecimal;

import eu.glowacki.utp.assignment02.employee.Employee;

public final class PayrollEntry {

	private final Employee _employee;
	private final BigDecimal _salaryPlusBonus;
	
	public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus) {
		_employee = employee;
		_salaryPlusBonus = salary==null&bonus==null?new BigDecimal(0):salary==null?bonus:bonus==null?salary:salary.add(bonus); // validate whether salary and bonus are not null
	}
	public Employee get_employee(){return _employee;}
	public BigDecimal get_salaryPlusBonus(){return _salaryPlusBonus;}

	@Override
	public boolean equals(Object o) {

		if (o == null || !(o instanceof PayrollEntry)) {
			return false;
		}
		if (get_employee().equals(((PayrollEntry) o).get_employee()) &&
				get_salaryPlusBonus().equals(((PayrollEntry) o).get_salaryPlusBonus())){
			return true;
		}
		return false;
	}

}