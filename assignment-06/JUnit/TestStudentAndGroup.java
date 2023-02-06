import Groups.Group;
import People.AssignDate;
import People.Nation;
import People.Student;
import People.Teacher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestStudentAndGroup {
    Student a, b, d;
    Group c;
    @Before
    public void INIT(){
        //Generator.GENERATE(100,10,12,3,10);
        Generator.CLEAR();
        a = Generator.getStudent();
        d = new Student(Nation.INIT_rand(),"AAAA","AAAA",new AssignDate().getDate());
    }
    @Test
    public void test(){
        Assert.assertEquals(a.hashCode(),Student.getThisList().get(0).hashCode());
        b = Generator.getStudent();
        Assert.assertNotEquals(a.hashCode(),b.hashCode());
        Assert.assertNotEquals(a.hashCode(),Student.getThisList().get(2).hashCode());
        Assert.assertEquals(a,Student.getThisList().get(0));
        //
        c = Generator.getGroup();
        c.addList(a);
        Assert.assertEquals(c,a.getGrp());
        //
        String tmp = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(a.getDob());
        tmp += cal.get(Calendar.YEAR);
        tmp += a.getDob().getMonth()+1;
        tmp += a.getDob().getDate();
        tmp += "00000";
        //
        List<Student> tmp2 = new ArrayList<>();
        tmp2.add(a);
        Assert.assertEquals(c.getNation(a.getNation()),tmp2);
        Assert.assertEquals(a.getSsn(),tmp);
        c.addList(b);
        c.addList(d);
        Assert.assertEquals(c.getList().get(0),d);
    }
}
