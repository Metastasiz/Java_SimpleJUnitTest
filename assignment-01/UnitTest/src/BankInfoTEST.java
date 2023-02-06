import eu.glowacki.utp.assignment01.BankInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankInfoTEST {
    private final String m = "pepegaClap";
    private BankInfo info;

    @Before
    public void constructor(){
        System.out.println("Now constructing info...");
        info = new BankInfo(m);
        System.out.println("1...");
    }

    @Test
    public void aggregate(){
        System.out.println("Now testing 1...");
        Assert.assertEquals(m,info.getName());
        System.out.println("1...");
        Assert.assertNotSame(m,info.getName());
        System.out.println("2...");
    }
    @Test
    public void deepClone(){
        System.out.println("Now testing 2...");
        Assert.assertEquals(info.deepClone().getName(),info.getName());
        System.out.println("1...");
        Assert.assertNotSame(info.deepClone().getName(),info.getName());
        System.out.println("2...");
    }

}
