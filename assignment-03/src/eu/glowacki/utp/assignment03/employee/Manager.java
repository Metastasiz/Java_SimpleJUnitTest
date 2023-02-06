package eu.glowacki.utp.assignment03.employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Manager extends Worker {
	
	// (assignment 02)
	// attributes:
	// * subordinates (a list of immediate subordinates)
	// * all subordinates (a list of subordinates in all hierarchy)
    private final List<Employee> _subs;
    private final List<Employee> _totalSubs;

    public Manager(String firstName, String lastName, Date dob, BigDecimal sal, BigDecimal bonus, Date start, Manager m) {
        super(firstName, lastName, dob, sal, bonus, start, m);
        _totalSubs = new ArrayList<>();
        _subs = new ArrayList<>();
    }
    public List<Employee> getSubs(){return _subs;}
    public List<Employee> getTotalSubs(){
        List<Employee> out = new ArrayList<>();
        for (Employee e: getSubs()){
            if (e instanceof Manager){
                Manager m = (Manager)e;
                if (m._totalSubs != null)out.addAll(((Manager) e).getTotalSubs());
            }
            out.add(e);
        }
        return out;
    }
    public void addSub(Employee emp){
        if (getManager() == emp || emp == this) return;
        _subs.add(emp);
        emp.setManager(this);
    }
}