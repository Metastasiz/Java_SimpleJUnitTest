package Groups;

import People.Nation;
import People.Person;
import People.Student;
import People.Teacher;

import java.util.*;

public class Department implements Comparable<Department>{
    private static int _counter = 0;
    private final int deptNo;
    private synchronized static int assignID(){return _counter++;}
    private final String name;
    private final List<Teacher> list = new ArrayList<>();
    public Department(String a){
        name = a;
        deptNo = assignID();
        thisList.add(this);
    }
    //
    private static List<Department> thisList = new ArrayList<>();
    public static List<Department> getThisList(){return thisList;}
    public static Department getThisRand(){if(getThisList().size() == 0)return null;return getThisList().get(new Random().nextInt(getThisList().size()));}
    public static void CLEAR(){thisList = new ArrayList<>();}
    //
    public String getName(){return name;}
    public int getNo(){return deptNo;}
    public int getSize(){return list.size();}
    public List<Teacher> getList(){return list;}
    public void addList(Teacher in){
        if (in.getDept() != null) return;
        list.add(in);
        in.assignDept(this);
        list.sort(Person.comp);
        INIT_filter();
    }
    public void addList(List<Teacher> in){
        for(Teacher e: in){
            if (e.getDept() != null) continue;
            list.add(e);
            e.assignDept(this);
        }
        list.sort(Person.comp);
        INIT_filter();
    }
    public void rmList(Teacher in){list.remove(in);}
    public void rmList(List<Teacher> in){for(Teacher e: in)rmList(e);}
    @Override
    public String toString(){
        return "\nDepartment "+getName() + " No. " + getNo() + " with " + getSize();
    }
    //
    public static final Comparator<Department> comp = Comparator.comparing(Department::getNo)
            .thenComparing(Department::getName)
            .thenComparing(Department::getSize);
    @Override
    public final int compareTo(Department in){
        return comp.compare(this,in);
    }
    //
    private Map<Nation, List<Teacher>> sortedByN;
    public List<Teacher> getNation(Nation n){return sortedByN.getOrDefault(n, null);}
    private void INIT_filter() {
        Map<Nation, List<Teacher>> out = new TreeMap<>();
        if (list == null)return;
        for (Person e : list){
            Nation find = e.getNation();
            if (out.get(find) == null)out.put(find, nationOf(find));
        }
        sortedByN = out;
    }
    private List<Teacher> nationOf(Nation n) {
        List<Teacher> out = new ArrayList<>();
        if (list == null)return out;
        for (Teacher e : list)if (e.getNation().equals(n))out.add(e);
        return out;
    }
    //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return deptNo == that.deptNo && Objects.equals(name, that.name) && Objects.equals(list, that.list) && Objects.equals(sortedByN, that.sortedByN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptNo, name, list, sortedByN);
    }
}
