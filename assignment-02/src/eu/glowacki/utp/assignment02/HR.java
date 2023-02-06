package eu.glowacki.utp.assignment02;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import eu.glowacki.utp.assignment02.employee.Employee;
import eu.glowacki.utp.assignment02.employee.Manager;
import eu.glowacki.utp.assignment02.employee.Trainee;
import eu.glowacki.utp.assignment02.employee.Worker;
import eu.glowacki.utp.assignment02.payroll.PayrollEntry;

public final class HR {

	public static List<PayrollEntry> payroll(List<Employee> emp) {
		if (emp == null) {
			return null;
		}
		//
		List<PayrollEntry> p = emp
				.stream()
				.map(e -> {if (e instanceof Worker) {return new PayrollEntry(e,e.getSalary(),((Worker) e).getBonus());}
							else return new PayrollEntry(e,e.getSalary(), new BigDecimal(0));})
				.collect(Collectors.toList());
		return p;
	}

	// payroll for all subordinates
	public static List<PayrollEntry> subordinatesPayroll(Manager mng) {
		if (mng == null) {
			return null;
		}
		//
		return payroll(mng.getTotalSubs());
	}

	public static BigDecimal bonusTotal(List<Employee> employees) {
		if (employees == null) {
			return null;
		}
		//
		BigDecimal r = employees
				.stream()
				.filter(e -> e instanceof Worker)
				.map(e -> ((Worker) e).getBonus())
				.reduce(new BigDecimal(0),(total, bonus) -> total.add(bonus));
		return r;
	}

	public static Employee getOldest(List<Employee> emp) {
		if (emp == null) {
			return null;
		}
		//
		Employee out = emp
				.stream()
				.reduce(emp.get(0), (a, b) -> {
							if (a.getDuration() < b.getDuration()) {return b;}
							else return a;
						});
		return out;
	}
	public static BigDecimal getHiSalNoB(List<Employee> emp) {
		if (emp == null) {
			return null;
		}
		//
		BigDecimal out = emp
				.stream()
				.map(Employee::getSalary)
				.reduce(emp.get(0).getSalary(),(a,b)->{
					if (a.compareTo(b) < 0){return b;}
					else return a;
				});
		return out;
	}
	public static BigDecimal getHiSalWiB(List<Employee> emp) {
		if (emp == null) {
			return null;
		}
		//
		BigDecimal out = emp
				.stream()
				.map(e -> {
					if (e instanceof Worker){return e.getSalary().add(((Worker) e).getBonus());}
					else return e.getSalary();
				})
				.reduce(emp.get(0).getSalary(),(a,b)->{
					if (a.compareTo(b) < 0){return b;}
					else return a;
				});
		return out;
	}
	public static List<Employee> getLastName_A_ofMng(Manager mng){
		if (mng == null) {
			return null;
		}
		//
		List<Employee> out = mng.getTotalSubs()
				.stream()
				.filter(e -> e.getLastName().toUpperCase(Locale.ROOT).startsWith("A"))
				.collect(Collectors.toList());
		return out;
	}
	public static List<Employee> getSalMore_1000(List<Employee> emp){
		if (emp == null) {
			return null;
		}
		//
		List<Employee> out = emp
				.stream()
				.filter(e -> (e.getSalary().compareTo(new BigDecimal(1000)) >= 0))
				.collect(Collectors.toList());
		return out;
	}


	/// ...
	// rest of the methods specified in the assignment description


	/**
	 * samples for functional processing in Java
	 *
	public static List<Short> getAges(List<Employee> employees) {
		if (employees == null) {
			return null;
		}
		List<Short> ages = employees //
				.stream() //
				.map(emp -> emp.getAge()) //
				.collect(Collectors.toList());
		return ages;
	}

	public static void printAges(List<Employee> employees) {
		if (employees == null) {
			return;
		}
		employees //
				.stream() //
				.map(emp -> (int) emp.getAge()) //
				.forEach(age -> System.out.print(age + ", "));
	}

	//
	// average age for the Employees whose first name starts with 'A' and they are older than 20
	public static short getAverageAgeInline(List<Employee> employees) {
		if (employees == null) {
			return 0;
		}
		int employeeTotalAge = employees //
				.stream() //
				.filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
				.map(emp -> (int) emp.getAge()) //
				.reduce(0, //
						(total, age) -> total + age);

		long filteredEmployeesCount = employees //
				.stream() //
				.filter(emp -> emp.getFirstName().startsWith("A") && emp.getAge() > 20) //
				.count();

		return (short) (employeeTotalAge / filteredEmployeesCount);
	}

	public static short getAverageAgeMethodReference(List<Employee> employees) {
		if (employees == null) {
			return 0;
		}
		int employeeTotalAge = employees //
				.stream() //
				.map(emp -> (int) emp.getAge()) //
				.reduce(0, HumanResourcesStatistics::totalAge);
		return (short) (employeeTotalAge / employees.size());
	}

	public static short getMaxAgeInline(List<Employee> employees) {
		short employeeMaxAge = employees //
				.stream() //
				.map(emp -> emp.getAge()) //
				.reduce((short) 0, //
						(maxAge, age) -> {
							if (maxAge < age) {
								return age;
							} else {
								return maxAge;
							}
						});
		return employeeMaxAge;
	}

	public static short getMaxAgeMethodReference(List<Employee> employees) {
		short employeeMaxAge = employees //
				.stream() //
				.map(emp -> emp.getAge()) //
				.reduce((short) 0, HumanResourcesStatistics::maxAge);
		return employeeMaxAge;
	}

	private static int totalAge(int totalAge, int age) {
		return totalAge + age;
	}

	private static short maxAge(short maxAge, short age) {
		if (maxAge < age) {
			return age;
		} else {
			return maxAge;
		}
	}
	 */
}