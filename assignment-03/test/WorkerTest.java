import eu.glowacki.utp.assignment03.employee.AssignDate;
import eu.glowacki.utp.assignment03.employee.Manager;
import eu.glowacki.utp.assignment03.employee.Worker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WorkerTest {
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
    public final Map<Integer, AssignDate> doss = new HashMap<>() {
        {
            put(0, new AssignDate(1,1,2001));
            put(1, new AssignDate(2,2,2002));
            put(2, new AssignDate(3,3,2003));
            put(3, new AssignDate(4,4,2004));
            put(4, new AssignDate(5,5,2005));
            put(5, new AssignDate(6,6,2017));
            put(6, new AssignDate(7,7,2018));
            put(7, new AssignDate(8,8,2019));
            put(8, new AssignDate(9,9,2020));
            put(9, new AssignDate(10,9,2021));
            put(10, new AssignDate(11, 1, 2011));
            put(11, new AssignDate(12, 2, 2012));
            put(12, new AssignDate(13, 3, 2013));
            put(13, new AssignDate(14, 4, 2014));
            put(14, new AssignDate(15, 5, 2015));
            put(15, new AssignDate(16, 6, 2016));
            put(16, new AssignDate(17, 7, 2017));
            put(17, new AssignDate(18, 8, 2018));
            put(18, new AssignDate(19, 9, 2019));
            put(19, new AssignDate(20,10,2020));
        }
    };
    //
    private Worker e06, e08, e09;

    @Before
    public void cons(){
        e06 = new Worker(fn.get(5),ln.get(5),dobs.get(5).getDate(),new BigDecimal(1001),new BigDecimal(200),doss.get(5).getDate(),null);
        e08 = new Worker(fn.get(7),ln.get(7),dobs.get(7).getDate(),new BigDecimal(1003),new BigDecimal(200),doss.get(7).getDate(),(Manager) null);
        e09 = new Worker(fn.get(8),ln.get(8),dobs.get(8).getDate(),new BigDecimal(1004),new BigDecimal(200),doss.get(8).getDate(),(Manager) null);
    }

    @Test
    public void allTest() throws Exception {
        BigDecimal a = new BigDecimal(200);

        Assert.assertEquals(doss.get(5).getDate(),e06.getStartDate());
        Assert.assertEquals(a,e06.getBonus());
        //
        e06.setBonus(new BigDecimal(1234));
        Assert.assertEquals(new BigDecimal(1234),e06.getBonus());

        Assert.assertEquals(new BigDecimal(-1),e08.compareSal2(e09));
        Assert.assertEquals(-1,e08.compareSal(e09));

        Assert.assertTrue(e09.earnMore(e08.getSalary()));
        Assert.assertFalse(e09.earnLess(e08.getSalary()));
        //
        Assert.assertEquals(e08.getAge() - e09.getAge(),e08.compareAge2(e09));
        Assert.assertEquals(1,e08.compareAge(e09));

        Assert.assertFalse(e09.isOlder(e08));
        Assert.assertTrue(e09.isYounger(e08));

        Assert.assertFalse(e09.isOlderYear(e09.getAge()));
        Assert.assertTrue(e09.isOlderYear(e09.getAge()-1));
    }

    @Test (expected = Exception.class)
    public void errorTest1() throws Exception {
        Assert.assertEquals(e08.getAge() - e09.getAge(),e08.compareAge2(null));
        Assert.assertEquals(1,e08.compareAge(null));
    }
    @Test (expected = Exception.class)
    public void errorTest2() throws Exception {
        Assert.assertFalse(e09.isOlder(null));
        Assert.assertTrue(e09.isYounger(null));
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void errorTest3() throws Exception {
        Assert.assertFalse(e09.isOlderYear(-1));
        Assert.assertTrue(e09.isOlderYear(-1));
    }
}
