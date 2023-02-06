import eu.glowacki.utp.assignment02.HR;
import eu.glowacki.utp.assignment02.employee.*;
import eu.glowacki.utp.assignment02.payroll.PayrollEntry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

public class HRTest {
    //too lazy to type
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
    private BigDecimal
             highestSal = new BigDecimal(2000)
            ,sal4highestBonus = new BigDecimal(1301)
            ,highestBonus = new BigDecimal(9999);
    //
    private List<Employee> total;
    private Employee e01, e02, e03, e04, e05, e06, e07, e08, e09, e10
            ,e11, e12, e13, e14, e15, e16, e17, e18, e19, e20;

    @Before
    public void constructor(){
        System.out.println("Constructing...");
        total = new ArrayList<>();

        e01 = new Manager(fn.get(0),ln.get(0),dobs.get(0).getDate(),highestSal,new BigDecimal(300),dobs.get(1).getDate(),null);
        e02 = new Manager(fn.get(1),ln.get(1),dobs.get(1).getDate(),sal4highestBonus,highestBonus,dobs.get(2).getDate(), (Manager) e01);
        e03 = new Manager(fn.get(2),ln.get(2),dobs.get(2).getDate(),new BigDecimal(1302),new BigDecimal(300),dobs.get(3).getDate(),(Manager) e01);
        e04 = new Manager(fn.get(3),ln.get(3),dobs.get(3).getDate(),new BigDecimal(1303),new BigDecimal(300),dobs.get(4).getDate(),(Manager) e02);
        e05 = new Manager(fn.get(4),ln.get(4),dobs.get(4).getDate(),new BigDecimal(1304),new BigDecimal(300),dobs.get(5).getDate(),(Manager) e03);

        e06 = new Worker(fn.get(5),ln.get(5),dobs.get(5).getDate(),new BigDecimal(1001),new BigDecimal(200),dobs.get(6).getDate(),(Manager) e04);
        e07 = new Worker(fn.get(6),ln.get(6),dobs.get(6).getDate(),new BigDecimal(1002),new BigDecimal(200),dobs.get(7).getDate(),(Manager) e01);
        e08 = new Worker(fn.get(7),ln.get(7),dobs.get(7).getDate(),new BigDecimal(1003),new BigDecimal(200),dobs.get(8).getDate(),(Manager) e01);
        e09 = new Worker(fn.get(8),ln.get(8),dobs.get(8).getDate(),new BigDecimal(1004),new BigDecimal(200),dobs.get(9).getDate(),(Manager) e02);
        e10 = new Worker(fn.get(9),ln.get(9),dobs.get(9).getDate(),new BigDecimal(1005),new BigDecimal(200),dobs.get(10).getDate(),(Manager) e03);

        e11 = new Trainee(fn.get(10),ln.get(10),dobs.get(10).getDate(),new BigDecimal(801),20,dobs.get(11).getDate(),(Manager) e05);
        e12 = new Trainee(fn.get(11),ln.get(11),dobs.get(11).getDate(),new BigDecimal(802),20,dobs.get(12).getDate(),(Manager) e05);
        e13 = new Trainee(fn.get(12),ln.get(12),dobs.get(12).getDate(),new BigDecimal(803),20,dobs.get(13).getDate(),(Manager) e05);
        e14 = new Trainee(fn.get(13),ln.get(13),dobs.get(13).getDate(),new BigDecimal(804),20,dobs.get(14).getDate(),(Manager) e05);
        e15 = new Trainee(fn.get(14),ln.get(14),dobs.get(14).getDate(),new BigDecimal(805),20,dobs.get(15).getDate(),(Manager) e05);
        e16 = new Trainee(fn.get(15),ln.get(15),dobs.get(15).getDate(),new BigDecimal(806),20,dobs.get(16).getDate(),(Manager) e05);
        e17 = new Trainee(fn.get(16),ln.get(16),dobs.get(16).getDate(),new BigDecimal(807),20,dobs.get(17).getDate(),(Manager) e05);
        e18 = new Trainee(fn.get(17),ln.get(17),dobs.get(17).getDate(),new BigDecimal(808),20,dobs.get(18).getDate(),(Manager) e05);
        e19 = new Trainee(fn.get(18),ln.get(18),dobs.get(18).getDate(),new BigDecimal(809),20,dobs.get(19).getDate(),(Manager) e05);
        e20 = new Trainee(fn.get(19),ln.get(19),dobs.get(19).getDate(),new BigDecimal(810),20,dobs.get(20).getDate(),(Manager) e05);

        total.add(e01);
        total.add(e02);
        total.add(e03);
        total.add(e04);
        total.add(e05);
        total.add(e06);
        total.add(e07);
        total.add(e08);
        total.add(e09);
        total.add(e10);
        total.add(e11);
        total.add(e12);
        total.add(e13);
        total.add(e14);
        total.add(e15);
        total.add(e16);
        total.add(e17);
        total.add(e18);
        total.add(e19);
        total.add(e20);
    }

    @Test
    public void payrollTest(){
        List<PayrollEntry> p = new ArrayList<>();
        for (Employee e : total) {
            if (e instanceof Worker) {
                p.add(new PayrollEntry(e, e.getSalary(), ((Worker) e).getBonus()));
            } else {
                p.add(new PayrollEntry(e, e.getSalary(), new BigDecimal(0)));
            }
        }
        Assert.assertEquals(p, HR.payroll(total));
        for (int i = 0; i < total.size(); i++){
            Assert.assertEquals(p.get(i),HR.payroll(total).get(i));
        }
    }
    @Test
    public void subordinatesPayroll(){
        Manager m = (Manager)e02;
        List<PayrollEntry> p = new ArrayList<>();
        for (Employee e : m.getTotalSubs()) {
            if (e instanceof Worker) {
                p.add(new PayrollEntry(e, e.getSalary(), ((Worker) e).getBonus()));
            } else {
                p.add(new PayrollEntry(e, e.getSalary(), new BigDecimal(0)));
            }
        }
        Assert.assertEquals(p,HR.payroll(m.getTotalSubs()));
        for (int i = 0; i < p.size(); i++){
            Assert.assertEquals(p.get(i),HR.payroll(m.getTotalSubs()).get(i));
        }
    }

    @Test
    public void bonusTotalTest(){
        BigDecimal tmp = new BigDecimal(0);
        for (Employee e : total){
            if (e instanceof Worker){
                tmp = tmp.add(((Worker) e).getBonus());
            }
        }
        Assert.assertEquals(tmp,HR.bonusTotal(total));
    }

    @Test
    public void getOldestTest(){
        Assert.assertEquals(e01,HR.getOldest(total));
    }

    @Test
    public void getHiSalNoBTest(){
        Assert.assertEquals(highestSal,HR.getHiSalNoB(total));
    }

    @Test
    public void getHiSalWiBTest(){
        Assert.assertEquals(sal4highestBonus.add(highestBonus),HR.getHiSalWiB(total));
    }
    //03,01
    //09,16
    @Test
    public void getLastName_A_ofMngTest(){
        List<Employee> a = new ArrayList<>();
        a.add(e10);
        a.add(e17);
        for (int i = 0; i < a.size(); i++){
            Assert.assertTrue(HR.getLastName_A_ofMng((Manager) e01).contains(a.get(i)));
        }
    }

    @Test
    public void getSalMore_1000Test(){
        List<Employee> a = new ArrayList<>();
        a.add(e01);
        a.add(e02);
        a.add(e03);
        a.add(e04);
        a.add(e05);
        a.add(e06);
        a.add(e07);
        a.add(e08);
        a.add(e09);
        a.add(e10);
        for (int i = 0; i < a.size(); i++){
            Assert.assertTrue(HR.getSalMore_1000(total).contains(a.get(i)));
        }

    }
}
