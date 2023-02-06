import Groups.*;
import People.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestGenerator {
    @Before
    public void INIT(){
        Generator.CLEAR();
        Generator.GENERATE(100,10,12,3,10);
    }
    @Test
    public void test(){
        Assert.assertEquals(Student.getThisList().size(),100);
        Assert.assertEquals(Teacher.getThisList().size(),10);
        Assert.assertEquals(Department.getThisList().size(),3);
        Assert.assertEquals(Group.getThisList().size(),12);
        Assert.assertEquals(Subject.getThisList().size(),10);
    }
}
