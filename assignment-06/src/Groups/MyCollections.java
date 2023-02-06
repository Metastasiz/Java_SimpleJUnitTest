package Groups;

import People.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyCollections {
    private static List<Department> listDept = new ArrayList<>();
    private static List<Group> listGrp = new ArrayList<>();
    private static List<Subject> listSubj = new ArrayList<>();
    //
    public static void addDept(Department in){listDept.add(in);listDept.sort(Department.comp);}
    public static void addDept(List<Department> in){for(Department e: in)listDept.add(e);listDept.sort(Department.comp);}
    public static void rmvDept(Department in){listDept.remove(in);}
    public static void rmvDept(List<Department> in){for(Department e: in)listDept.remove(e);}
    //
    public static void addGrp(Group in){listGrp.add(in);listGrp.sort(Group.comp);}
    public static void addGrp(List<Group> in){for(Group e: in)listGrp.add(e);listGrp.sort(Group.comp);}
    public static void rmvGrp(Group in){listGrp.remove(in);}
    public static void rmvGrp(List<Group> in){for(Group e: in)listGrp.remove(e);}
    //
    public static void addSubj(Subject in){listSubj.add(in);listSubj.sort(Subject.comp);}
    public static void addSubj(List<Subject> in){for(Subject e: in)listSubj.add(e);listSubj.sort(Subject.comp);}
    public static void rmvSubj(Subject in){listSubj.remove(in);}
    public static void rmvSubj(List<Subject> in){for(Subject e: in)listSubj.remove(e);}
    //

}
