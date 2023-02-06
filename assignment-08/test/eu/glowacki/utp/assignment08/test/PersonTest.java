package eu.glowacki.utp.assignment08.test;

import eu.glowacki.utp.assignment08.AssignDate;
import eu.glowacki.utp.assignment08.Assignment08Exception;
import eu.glowacki.utp.assignment08.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;

public class PersonTest {
    public Person p1, p2, p3, p4;
    @Before
    public void constructor(){
        p1 = new Person("A","A",new AssignDate("2000-1-1").getDate());
        p2 = new Person("B","A",new AssignDate("2000-1-1").getDate());
        p3 = new Person("B","B",new AssignDate("1999-1-1").getDate());
        p4 = new Person("B","B",new AssignDate("2000-1-1").getDate());
    }
    @Test
    public void testAssign08() throws FileNotFoundException, Assignment08Exception {
        DataOutputStream dout = new DataOutputStream(new FileOutputStream(Person.myPath));
        p1.serialize(dout);
        Person in = new Person(null,null,null);
        Assert.assertNotEquals(in,p1);

        DataInputStream din = new DataInputStream(new FileInputStream(Person.myPath));
        in = Person.deserialize(din);
        Assert.assertEquals(in,p1);
    }

    @Test
    public void testAll(){
        Assert.assertEquals(-1,p1.compareTo(p2));
        Assert.assertEquals(-1,p2.compareTo(p3));
        Assert.assertEquals(-1,p3.compareTo(p4));
    }

}
