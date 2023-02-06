import Groups.Department;
import Groups.Group;
import Groups.Subject;
import People.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Generator {
    /*
    100 students
    12 groups
    3 depts
    10 subs
    10 teachers
     */
    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase(Locale.ROOT);
    private static final String digits = "0123456789";
    private static final String[] chars = {upper,lower};
    public static String randDigit(int bound){
        String out = "";
        for (int i = 0; i < bound; i++){
            out += digits.charAt(new Random().nextInt(digits.length()));
        }
        return out;
    }
    public static String randString(int bound){
        String out = "";
        out += upper.charAt(new Random().nextInt(upper.length()));
        int l = new Random().nextInt(bound);
        for (int i = 0; i < l; i++) {
            int tmp = new Random().nextInt(chars.length);
            out += lower.charAt(new Random().nextInt(lower.length()));
        }
        return out;
    }
    public static void GENERATE(int students, int teachers, int groups, int departments, int subjects){
        for (int i = 0; i < students; i++)getStudent();
        for (int i = 0; i < teachers; i++)getTeacher();
        for (int i = 0; i < groups; i++)getGroup();
        assignGroup();
        for (int i = 0; i < departments; i++)getDepartment();
        assignDepartment();
        for (int i = 0; i < subjects; i++)getSubject();
        assignSubject();
    }
    public static Student getStudent(){
        Student out = new Student(Nation.INIT_rand(), randString(MyTools.myRandom(5,10)), randString(MyTools.myRandom(5,10)), new AssignDate(AssignDate.RAND).getDate());
        return out;
    }
    public static Teacher getTeacher(){
        Date tmp = new AssignDate(AssignDate.RAND).getDate();
        Teacher out = new Teacher(Nation.INIT_rand(), randString(MyTools.myRandom(5,10)), randString(MyTools.myRandom(5,10)), tmp, new AssignDate(tmp.getYear()+MyTools.myRandom(20,30),tmp.getMonth()+1,tmp.getDate()).getDate());
        return out;
    }
    public static Department getDepartment(){
        Department out = new Department(randString(MyTools.myRandom(5,10)));
        return out;
    }
    public static void assignDepartment(){
        for (Teacher e : Teacher.getThisList()){
            Department.getThisRand().addList(e);
        }
    }
    public static Group getGroup(){
        Group out = new Group(randString(MyTools.myRandom(5,10)));
        return out;
    }
    public static void assignGroup(){
        for (Student e : Student.getThisList()){
            Group tmp = Group.getThisRand();
            tmp.addList(e);

        }
    }
    public static Subject getSubject(){
        Teacher tmp = Teacher.getThisRand();
        Subject out = new Subject(randString(MyTools.myRandom(5,10)),tmp.getDept(),tmp);
        return out;
    }
    public static void assignSubject(){
        for (Group e : Group.getThisList()){
            Subject.getThisRand().addList(e);
        }
    }
    public static void CLEAR(){
        Department.CLEAR();
        Group.CLEAR();
        Subject.CLEAR();
        Person.CLEAR();
        Student.CLEAR();
        Teacher.CLEAR();
    }
    public static void main(String[] arg){
        GENERATE(10,3,2,2,2);
        System.out.println(Student.getThisList());
        System.out.println(Group.getThisList());
        System.out.println(Teacher.getThisList());
        System.out.println(Department.getThisList());
        System.out.println(Subject.getThisList());
    }
}
