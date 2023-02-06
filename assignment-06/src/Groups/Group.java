package Groups;

import People.Nation;
import People.Person;
import People.Student;
import People.Teacher;

import java.util.*;

public class Group implements Comparable<Group>{
    private static int _counter = 0;
    private final int grpNo;
    private synchronized static int assignID(){return _counter++;}
    private final String name;
    private final List<Student> list = new ArrayList<>();
    public Group(String a){
        name = a;
        grpNo = assignID();
        thisList.add(this);
    }
    //
    private static List<Group> thisList = new ArrayList<>();
    public static List<Group> getThisList(){return thisList;}
    public static Group getThisRand(){if(getThisList().size() == 0)return null;return getThisList().get(new Random().nextInt(getThisList().size()));}
    public static void CLEAR(){thisList = new ArrayList<>();}
    //
    public String getName(){return name;}
    public int getNo(){return grpNo;}
    public int getSize(){return list.size();}
    public List<Student> getList(){return list;}
    public void addList(Student in){
        if (in.getGrp() != null) return;
        list.add(in);
        in.assignGrp(this);
        list.sort(Person.comp);
        INIT_filter();
    }
    public void addList(List<Student> in){
        for(Student e: in){
            if (e.getGrp() != null) continue;
            addList(e);
            e.assignGrp(this);
        }
        list.sort(Person.comp);
        INIT_filter();
    }
    public void rmList(Student in){list.remove(in);}
    public void rmList(List<Student> in){for(Student e: in)rmList(e);}
    @Override
    public String toString(){
        return "\nGroup "+getName() + " No. " + getNo() + " with " + getSize();
    }
    //
    public static final Comparator<Group> comp = Comparator.comparing(Group::getNo)
            .thenComparing(Group::getName)
            .thenComparing(Group::getSize);
    @Override
    public final int compareTo(Group in){
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
        Group group = (Group) o;
        return grpNo == group.grpNo && Objects.equals(name, group.name) && Objects.equals(list, group.list) && Objects.equals(sortedByN, group.sortedByN);
    }
    @Override
    public int hashCode() {
        return Objects.hash(grpNo, name, list, sortedByN);
    }
    public static void main(String[] arg){
        List<String> a = new ArrayList<>();
        a.add("asdf");
        a.add("dfg");
        for (int i = 0; i < 10; i++){
            System.out.println(new Random().nextInt(a.size()));
        }
    }

}
