import People.*;
import Groups.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TestTeacherAndDepartment {
    Teacher a, b, d;
    Department c;
    Subject k;
    @Before
    public void INIT(){
        //Generator.GENERATE(100,10,12,3,10);
        Generator.CLEAR();
        a = Generator.getTeacher();
        d = new Teacher(Nation.INIT_rand(),"AAAA","AAAA",new AssignDate().getDate(), new AssignDate().getDate());
    }
    @Test
    public void test(){
        Assert.assertEquals(a.hashCode(),Teacher.getThisList().get(0).hashCode());
        b = Generator.getTeacher();
        Assert.assertNotEquals(a.hashCode(),b.hashCode());
        Assert.assertNotEquals(a.hashCode(),Teacher.getThisList().get(2).hashCode());
        Assert.assertEquals(a,Teacher.getThisList().get(0));
        //
        c = Generator.getDepartment();
        c.addList(a);
        Assert.assertEquals(c,a.getDept());
        //
        String tmp = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(a.getDob());
        tmp += cal.get(Calendar.YEAR);
        tmp += a.getDob().getMonth()+1;
        tmp += a.getDob().getDate();
        tmp += "00000";
        //
        Assert.assertEquals(a.getSsn(),tmp);
        //
        //
        List<Teacher> tmp2 = new ArrayList<>();
        tmp2.add(a);
        Assert.assertEquals(c.getNation(a.getNation()),tmp2);
        Assert.assertEquals(c.getList().get(0),a);
        c.addList(b);
        c.addList(d);
        Assert.assertEquals(c.getList().get(0),d);
        //
        k = new Subject("something",a.getDept(),a);
        Assert.assertEquals(c,k.getSpvDept());
    }
}
