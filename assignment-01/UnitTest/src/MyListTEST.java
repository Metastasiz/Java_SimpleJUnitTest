import eu.glowacki.utp.assignment01.BankInfo;
import eu.glowacki.utp.assignment01.BankMoni;
import eu.glowacki.utp.assignment01.MyList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MyListTEST {
    public final String pepe1 = "you", pepe2 = "pepeg";
    public final int gib1 = 500, gib2 = 850;

    private BankInfo info1, info2;
    private BankMoni moni1, moni2;
    private List<BankInfo> _bankInfo;
    private List<BankMoni> _bankMoni;

    private MyList MyList1, MyList2;

    @Before
    public void Before(){
        System.out.println("Now constructing my list...");
        info1 = new BankInfo(pepe1);
        info2 = new BankInfo(pepe2);
        moni1 = new BankMoni(gib1);
        moni2 = new BankMoni(gib2);
        System.out.println("1...");
        Assert.assertEquals(pepe1,info1.getName());
        Assert.assertEquals(pepe2,info2.getName());
        System.out.println("2...");
        Assert.assertEquals(gib1, moni1.getMoni().getValue());
        Assert.assertEquals(gib2, moni2.getMoni().getValue());
        System.out.println("3...");
        _bankInfo = new ArrayList<>();
        _bankMoni = new ArrayList<>();
        _bankInfo.add(info1);
        _bankInfo.add(info2);
        _bankMoni.add(moni1);
        _bankMoni.add(moni2);
        System.out.println("4...");
        MyList1 = new MyList(_bankInfo);
        MyList2 = new MyList(_bankMoni);
        System.out.println("5...");
    }

    @Test
    public void elements(){
        System.out.println("Now testing 1...");
        Assert.assertSame(_bankInfo,MyList1.elements());
        Assert.assertSame(_bankMoni,MyList2.elements());
        System.out.println("1...");
        Assert.assertSame(_bankInfo.get(0),MyList1.elements().get(0));
        Assert.assertSame(_bankMoni.get(0),MyList2.elements().get(0));
        System.out.println("2...");
    }
    @Test
    public void aggregateALLElements(){
        System.out.println("Now testing 2...");
        Assert.assertNotEquals(_bankInfo.get(_bankInfo.size()-1).getName(),MyList1.aggregateAllElements());
        Assert.assertNotEquals(_bankMoni.get(_bankMoni.size()-1).getMoni(),MyList2.aggregateAllElements());
        System.out.println("1...");
        Assert.assertEquals(MyList1.aggregateAllElements(),_bankInfo.get(_bankInfo.size()-1).getName());
        Assert.assertEquals(MyList2.aggregateAllElements(),_bankMoni.get(_bankMoni.size()-1).getMoni());
        System.out.println("2...");
    }
    @Test
    public void cloneElementAtIndex() throws Exception {
        System.out.println("Now testing 3...");
        Assert.assertNotSame(_bankInfo.get(_bankInfo.size()-1),MyList1.cloneElementAtIndex(_bankInfo.size()-1));
        Assert.assertNotSame(_bankMoni.get(_bankMoni.size()-1),MyList2.cloneElementAtIndex(_bankInfo.size()-1));
        System.out.println("1...");

    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void exceptionTest1() throws Exception {
        System.out.println("Now testing 4...");
        MyList1.cloneElementAtIndex(_bankInfo.size());
        System.out.println("NOT PASS");
    }
}
