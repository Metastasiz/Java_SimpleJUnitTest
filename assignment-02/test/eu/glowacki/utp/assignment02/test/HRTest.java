package eu.glowacki.utp.assignment02.test;

import java.util.List;

import org.junit.Test;

import eu.glowacki.utp.assignment02.HR;
import eu.glowacki.utp.assignment02.employee.Employee;

public class HRTest {
	
	// Create a HR structure which resembles the below one:
	//
	// Director (an instance of Manager class (Director does not have a manager)
	//   |- Manager01
	//        |- Worker
	//        |- Worker
	//        |- Trainee
	//        |- ...
	//   |- Manager02
	//        |- ...
	//   |- ...
	//   |- Worker
	//   |- Worker
	//   |- Trainee
	
	private List<Employee> _allEmployees; // all employees ---  i.e. Workers, Trainees and their Managers and top Director (also an instance of Manager class)
	
	@Test
	public void payroll() {
		HR.payroll(_allEmployees);
	}

	@Test
	public void subordinatesPayroll() {
		HR.subordinatesPayroll(null);
	}


	/// ...
	// rest of the methods specified in the assignment description
}