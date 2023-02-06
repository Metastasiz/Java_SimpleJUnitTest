import eu.glowacki.utp.assignment03.employee.AssignDate;
import eu.glowacki.utp.assignment03.employee.Employee;
import eu.glowacki.utp.assignment03.employee.Manager;
import eu.glowacki.utp.assignment03.employee.Trainee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

public class ManagerTest {
    public final Map<Integer, String> fn = new HashMap<>() {
        {
            put(0, "A");
            put(1, "B");
            put(2, "C");
            put(3, "D");
            put(4, "E");
            put(5, "F");
            put(6, "G");
            put(7, "H");
            put(8, "I");
            put(9, "J");
            put(10, "K");
            put(11, "L");
            put(12, "M");
            put(13, "N");
            put(14, "O");
            put(15, "P");
            put(16, "Q");
            put(17, "R");
            put(18, "S");
            put(19, "T");
        }
    };
    public Map<Integer, String> ln = new HashMap<>() {
        {
            put(0, "1A".toLowerCase(Locale.ROOT));
            put(1, "1B".toLowerCase(Locale.ROOT));
            put(2, "1C".toLowerCase(Locale.ROOT));
            put(3, "1D".toLowerCase(Locale.ROOT));
            put(4, "1E".toLowerCase(Locale.ROOT));
            put(5, "1F".toLowerCase(Locale.ROOT));
            put(6, "1G".toLowerCase(Locale.ROOT));
            put(7, "1H".toLowerCase(Locale.ROOT));
            put(8, "1I".toLowerCase(Locale.ROOT));
            put(9, "AJ".toLowerCase(Locale.ROOT));
            put(10, "1K".toLowerCase(Locale.ROOT));
            put(11, "1L".toLowerCase(Locale.ROOT));
            put(12, "1M".toLowerCase(Locale.ROOT));
            put(13, "1N".toLowerCase(Locale.ROOT));
            put(14, "1O".toLowerCase(Locale.ROOT));
            put(15, "1P".toLowerCase(Locale.ROOT));
            put(16, "AQ".toLowerCase(Locale.ROOT));
            put(17, "1R".toLowerCase(Locale.ROOT));
            put(18, "1S".toLowerCase(Locale.ROOT));
            put(19, "1T".toLowerCase(Locale.ROOT));
        }
    };
    public final Map<Integer, AssignDate> dobs = new HashMap<>() {
        {
            put(0, new AssignDate(1,1,1901));
            put(1, new AssignDate(2,2,1902));
            put(2, new AssignDate(3,3,1903));
            put(3, new AssignDate(4,4,1904));
            put(4, new AssignDate(5,5,1905));
            put(5, new AssignDate(6,6,1906));
            put(6, new AssignDate(7,7,1907));
            put(7, new AssignDate(8,8,1908));
            put(8, new AssignDate(9,9,1909));
            put(9, new AssignDate(10,10,1910));
            put(10, new AssignDate(11, 1, 1911));
            put(11, new AssignDate(12, 2, 1912));
            put(12, new AssignDate(13, 3, 1913));
            put(13, new AssignDate(14, 4, 1914));
            put(14, new AssignDate(15, 5, 1915));
            put(15, new AssignDate(16, 6, 1916));
            put(16, new AssignDate(17, 7, 1917));
            put(17, new AssignDate(18, 8, 1918));
            put(18, new AssignDate(19, 9, 1919));
            put(19, new AssignDate(20,10,1920));
            put(20, new AssignDate(21,1,1921));
        }
    };
    //
    private Manager e01, e02;
    private Trainee e20;
    private BigDecimal
            highestSal = new BigDecimal(2000)
            ,sal4highestBonus = new BigDecimal(1301)
            ,highestBonus = new BigDecimal(9999);

    @Before
    public void cons(){
        e01 = new Manager(fn.get(0),ln.get(0),dobs.get(0).getDate(),highestSal,new BigDecimal(300),dobs.get(1).getDate(),null);
        e02 = new Manager(fn.get(1),ln.get(1),dobs.get(1).getDate(),sal4highestBonus,highestBonus,dobs.get(2).getDate(), null);
        e20 = new Trainee(fn.get(19),ln.get(19),dobs.get(19).getDate(),new BigDecimal(810),20,dobs.get(20).getDate(),(Manager) e02);
    }
    @Test
    public void allTest(){
        List<Employee> s = new ArrayList<>();

        s.add(e02);
        Assert.assertNotEquals(s,e01.getSubs());
        Assert.assertEquals(null,e02.getManager());

        e01.addSub(e02);
        Assert.assertEquals(s,e01.getSubs());

        s.add(e20);
        for (int i = 0; i < s.size(); i++)
        Assert.assertTrue(e01.getTotalSubs().contains(s.get(i)));


    }
}
