import eu.glowacki.utp.assignment01.BankMoni;
import eu.glowacki.utp.assignment01.mType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankMoniTEST {
    private final mType m = new mType(200);
    private BankMoni moni;

    @Before
    public void constructor(){
        System.out.println("Now constructing moni...");
        moni = new BankMoni(m.getValue());
        System.out.println("1...");
    }

    @Test
    public void aggregate(){
        System.out.println("Now testing 1...");
        Assert.assertEquals(m.getValue(),moni.getMoni().getValue());
        System.out.println("1...");
        Assert.assertNotSame(m,moni.getMoni());
        System.out.println("2...");
    }
    @Test
    public void deepClone(){
        System.out.println("Now testing 2...");
        Assert.assertNotSame(moni.deepClone().getMoni(),moni.getMoni());
        System.out.println("1...");

    }

}
