package Groups;

import People.Nation;
import People.Person;
import People.Student;
import People.Teacher;

import java.util.*;

public class Subject implements Comparable<Subject> {
    private static int _counter = 0;
    private final int subjNo;
    private synchronized static int assignID(){return _counter++;}
    private final String name;
    private Department spvDept;
    private Teacher lecturer;
    private List<Group> grp = new ArrayList<>();
    private final List<Student> list = new ArrayList<>();
    public Subject(String a, Department dept, Teacher t){
        name = a;
        spvDept = dept;
        lecturer = t;
        subjNo = assignID();
        thisList.add(this);
    }
    //
    private static List<Subject> thisList = new ArrayList<>();
    public static List<Subject> getThisList(){return thisList;}
    public static Subject getThisRand(){if(getThisList().size() == 0)return null;return getThisList().get(new Random().nextInt(getThisList().size()));}
    public static void CLEAR(){thisList = new ArrayList<>();}
    //
    public String getName(){return name;}
    public int getNo(){return subjNo;}
    public Department getSpvDept(){return spvDept;}
    public Teacher getLecturer(){return lecturer;}
    //
    public int getSize(){return list.size();}
    public List<Student> getList(){return list;}
    public void addList(Student in){list.add(in);INIT_filter();}
    public void addList(List<Student> in){for(Student e: in)addList(e);INIT_filter();}
    public void addList(Group in){for(Student e: in.getList())addList(e);grp.add(in);INIT_filter();}
    public void rmList(Student in){list.remove(in);}
    public void rmList(List<Student> in){for(Student e: in)rmList(e);}
    //
    @Override
    public String toString(){
        return "\n"+getSpvDept() + " - Class " + getName() + getNo() + ", lectured by " + getLecturer() + " with " + getSize() + " group " + grp;
    }
    //
    public static final Comparator<Subject> comp = Comparator.comparing(Subject::getNo)
            .thenComparing(Subject::getName)
            .thenComparing(Subject::getSize);
    @Override
    public final int compareTo(Subject in){
        return comp.compare(this,in);
    }
    //
    private Map<Nation, List<Student>> sortedByN;
    public List<Student> getNation(Nation n){return sortedByN.getOrDefault(n, null);}
    private void INIT_filter() {
        Map<Nation, List<Student>> out = new TreeMap<>();
        if (list == null)return;
        for (Person e : list){
            Nation find = e.getNation();
            if (out.get(find) == null)out.put(find, nationOf(find));
        }
        sortedByN = out;
    }
    private List<Student> nationOf(Nation n) {
        List<Student> out = new ArrayList<>();
        if (list == null)return out;
        for (Student e : list)if (e.getNation().equals(n))out.add(e);
        return out;
    }
    //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return subjNo == subject.subjNo && Objects.equals(name, subject.name) && Objects.equals(spvDept, subject.spvDept) && Objects.equals(lecturer, subject.lecturer) && Objects.equals(list, subject.list) && Objects.equals(sortedByN, subject.sortedByN);
    }
    @Override
    public int hashCode() {
        return Objects.hash(subjNo, name, spvDept, lecturer, list, sortedByN);
    }
}
