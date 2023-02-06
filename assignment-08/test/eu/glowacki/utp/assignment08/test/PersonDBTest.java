package eu.glowacki.utp.assignment08.test;

import eu.glowacki.utp.assignment08.AssignDate;
import eu.glowacki.utp.assignment08.Assignment08Exception;
import eu.glowacki.utp.assignment08.Person;
import eu.glowacki.utp.assignment08.PersonDB;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDBTest {
    private Person p1, p2, p3, p4;
    private List<Person> list = new ArrayList<>();
    private PersonDB DB;
    @Before
    public void constructor(){
        p1 = new Person("A","A",new AssignDate("2000-1-1").getDate());
        p2 = new Person("B","A",new AssignDate("2000-1-1").getDate());
        p3 = new Person("B","B",new AssignDate("1999-1-1").getDate());
        p4 = new Person("B","B",new AssignDate("2000-1-1").getDate());
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        DB = new PersonDB(list);
    }
    @Test
    public void testAssign08() throws FileNotFoundException, Assignment08Exception {
        DataOutputStream dout = new DataOutputStream(new FileOutputStream(Person.myPath));
        DB.serialize(dout);

        DataInputStream din = new DataInputStream(new FileInputStream(Person.myPath));
        PersonDB in = PersonDB.deserialize(din);
        Assert.assertEquals(in,DB);
    }
    @Test
    public void testAll(){
        List<Person> searchByDOB1 = new ArrayList<>();
        searchByDOB1.add(p1);
        searchByDOB1.add(p2);
        searchByDOB1.add(p4);
        List<Person> searchByDOB2 = new ArrayList<>();
        searchByDOB2.add(p3);
        //
        List<Person> a1 = DB.getSearchByB(p1.getDOB());
        List<Person> a2 = DB.getSearchByB(p3.getDOB());
        Assert.assertEquals(searchByDOB1,a1);
        Assert.assertEquals(searchByDOB2,a2);
        //
        Assert.assertEquals(DB.getSortedByN().get(0),p1);
        //
        Assert.assertEquals(DB.getSortedBySNB().get(0),p1);
        Assert.assertEquals(DB.getSortedBySNB().get(1),p2);
        Assert.assertEquals(DB.getSortedBySNB().get(2),p3);
        Assert.assertEquals(DB.getSortedBySNB().get(3),p4);
        //
        Assert.assertEquals(DB.getSortedByB().get(0),p3);
        //
        Assert.assertEquals(DB.bornOnDay(new AssignDate("2000-1-1").getDate()), searchByDOB1);
    }

}
