package People;

import Groups.Department;

import java.util.*;

public class Teacher extends Person {
    private final AcademicDeg academicDeg;
    private final Date hiredate;
    private Department dept = null;
    public Teacher(Nation n, String b, String c, Date date, Date hdate) {
        super(n, b, c, date);
        hiredate = hdate;
        academicDeg = AcademicDeg.INIT_rand();
        thisList.add(this);
    }
    //
    private static List<Teacher> thisList = new ArrayList<>();
    public static List<Teacher> getThisList(){return thisList;}
    public static Teacher getThisRand(){if(getThisList().size() == 0)return null;return getThisList().get(new Random().nextInt(getThisList().size()));}
    public static void CLEAR(){thisList = new ArrayList<>();}
    //
    public Department getDept(){return dept;}
    public void assignDept(Department a){dept=a;}
    //
    public AcademicDeg getAcademicDeg(){return academicDeg;}
    public Date getHiredate(){return hiredate;}
    //
    @Override
    public String toString(){
        return "\n"+getSsn() + " " + academicDeg + getLname().charAt(0) + ". " + getFname()  + ", is " + getNation() + " " + timeFormat.format(getDob()) + " from " + getDept();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return academicDeg == teacher.academicDeg && Objects.equals(hiredate, teacher.hiredate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(academicDeg, hiredate);
    }
}
