package eu.glowacki.utp.assignment03;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import eu.glowacki.utp.assignment03.employee.Employee;
import eu.glowacki.utp.assignment03.employee.Trainee;
import eu.glowacki.utp.assignment03.employee.Worker;

public final class HR {
	
	private HR() {}

	public static boolean emYearBetween(Employee e, int lo, int up) throws Exception {
		if (lo < 0 || up < 0) throw new IndexOutOfBoundsException("out of bound boi");
		if (!(e instanceof Worker)) return false;
		if (((Worker) e).moreThanYear(lo-1) && !(((Worker) e).moreThanYear(up))) return true;
		return false;
	}

	// The best solution is to implement the below features as static methods having a list of Employee as the first input argument.
	// In addition the list of arguments may be augmented with parameters required for the given feature.
	// najlepiej zaimplementowa� poni�sze metody jako statyczne, gdzie argumentem lista pracownik�w i odpowiednio dodatkowo to co wymagane w danym punkcie
	
	// (assignment 03)
	// methods:
	//
	// * search for Employees older than given employee and earning less than him
	//   wyszukaj osoby zatrudnione (Employee), kt�re s� starsze od podanej innej zatrudnionej osoby oraz zarabiaj� mniej od niej
	public static List<Employee> olderThanAndEarnMore(List<Employee> allEmp, Employee emp) {
		if (emp == null || allEmp == null) {
			return null;
		}
		//
		Predicate<Employee> older = e -> e.isOlder(emp);
		Predicate<Employee> earnsMore = e -> {
			try {
				return e.earnMore(emp.getSalary());
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			return false;
		};
		Predicate<Employee> olderAndEarnsMore = older.and(earnsMore);

		List<Employee> p = allEmp
				.stream()
				.filter(olderAndEarnsMore)
				.collect(Collectors.toList());
		return p;
	}
	
	//
	// * search for Trainees whose practice length is longer than given number of days and raise their salary by 5%
	//   wyszukaj praktykant�w (Trainee), kt�rych praktyka jest d�u�sza od podanej liczby dni,
	//   a nast�pnie podnie� ich uposa�enie o 5%
	public static List<Trainee> practiceLengthLongerThan(List<Employee> allEmp, int day) {
		if (allEmp == null || day < 0) {
			return null;
		}
		//
		List<Trainee> p = allEmp
				.stream()
				.filter(e -> {
					if((e instanceof Trainee) && (((Trainee) e).emLonger(day))){
						e.setSalaryPercent(1.05);
						return true;
					}
					return false;
				})
				.map(e -> ((Trainee) e))
				.collect(Collectors.toList());
		return p;
	}
	//
	// * search for Workers whose seniority is longer than given number of months and give them bonus of 300 if their bonus is smaller
	//   wyszukaj pracownik�w o sta�u d�u�szym ni� podana liczba miesi�cy,
	//   a nast�pnie przyznaj im premi� w wysoko�ci 300 je�li ich premia jest ni�sza
	public static List<Worker> seniorityLongerThan(List<Employee> allEmp, int month) {
		if (allEmp == null || month < 0) {
			return null;
		}
		//
		List<Worker> p = allEmp
				.stream()
				.filter(e -> {
					try {
						if((e instanceof Worker) && (((Worker) e).moreThanMonth(month))){
							if (!((Worker) e).moreThanBonus(new BigDecimal(300))){
								((Worker) e).setBonus(new BigDecimal(300));
							}
							return true;
						}
					} catch (Exception exception) {
						exception.printStackTrace();
					}
					return false;
				})
				.map(e -> ((Worker) e))
				.collect(Collectors.toList());
		return p;
	}
	
	//
	// * search for Workers whose seniority is between 1 and 3 years and give them raise of salary by 10%
	//   wyszukaj pracownik�w o sta�u pomi�dzy 1 a 3 lata i przyznaj im podwy�k� w wysoko�ci 10%
	public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmp) {
		if (allEmp == null) {
			return null;
		}
		//
		List<Worker> p = allEmp
				.stream()
				.filter(e -> {
					try {
						if(emYearBetween(e,1,3)){
							e.setSalaryPercent(1.1);
							return true;
						}
					} catch (Exception exception) {
						exception.printStackTrace();
					}
					return false;
				})
				.map(e -> ((Worker) e))
				.collect(Collectors.toList());
		return p;
	}
	
	//
	// * search for Workers whose seniority is longer than the seniority of a given employee and earn less than him and align their salary with the given employee
	//   wyszukaj pracownik�w o sta�u d�u�szym ni� sta� podanego pracownika i kt�rzy zarabiaj� mniej od niego,
	//   nast�pnie zr�wnaj ich wynagrodzenie z wynagrodzeniem danego pracownika
	public static List<Worker> seniorityLongerThan(List<Employee> allEmp, Employee emp) {
		if (allEmp == null || emp == null) {
			return null;
		}
		//
		List<Worker> p = allEmp
				.stream()
				.filter(e -> {
					try {
						if((e instanceof Worker) && (e.getDurationEmDay()>emp.getDurationEmDay()) && e.compareSal(emp)<=-1){
							e.setSalary(emp.getSalary());
							return true;
						}
					} catch (Exception exception) {
						exception.printStackTrace();
					}
					return false;
				})
				.map(e -> ((Worker) e))
				.collect(Collectors.toList());
		return p;
	}
	
	//
	// * search for Workers whose seniority is between 2 and 4 years and whose age is greater than given number of years
	//   wyszukaj pracownik�w o sta�u pomi�dzy 2 i 4 lata i starszych ni� podana liczba lat
	public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmp, int age) {
		if (allEmp == null || age < 0) {
			return null;
		}
		//
		List<Worker> p = allEmp
				.stream()
				.filter(e -> {
					try {
						if(emYearBetween(e,2,4) && e.isOlderYear(age)){
							return true;
						}
					} catch (Exception exception) {
						exception.printStackTrace();
					}
					return false;
				})
				.map(e -> ((Worker) e))
				.collect(Collectors.toList());
		return p;
	}
}