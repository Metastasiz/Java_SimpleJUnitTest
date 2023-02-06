package People;

import Groups.Department;
import Groups.Group;
import Groups.Subject;

import java.util.*;

public class Student extends Person{
    private static int _counter = 0;
    private final int studentNo;
    private Group grp = null;
    private synchronized static int assignID(){return _counter++;}
    public Student(Nation n, String b, String c, Date date) {
        super(n, b, c, date);
        studentNo = assignID();
        thisList.add(this);
    }
    //
    private static List<Student> thisList = new ArrayList<>();
    public static List<Student> getThisList(){return thisList;}
    public static Student getThisRand(){if(getThisList().size() == 0)return null;return getThisList().get(new Random().nextInt(getThisList().size()));}
    public static void CLEAR(){thisList = new ArrayList<>();}
    //
    public Group getGrp(){return grp;}
    public void assignGrp(Group a){grp=a;}
    //
    public int getStudentNo(){return studentNo;}
    //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentNo == student.studentNo;
    }
    @Override
    public int hashCode() {
        return Objects.hash(studentNo);
    }
}
