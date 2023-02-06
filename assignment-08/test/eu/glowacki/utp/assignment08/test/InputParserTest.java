package eu.glowacki.utp.assignment08.test;

import eu.glowacki.utp.assignment08.AssignDate;
import eu.glowacki.utp.assignment08.InputParser;
import eu.glowacki.utp.assignment08.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class InputParserTest {
    private Person px, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11;
    private List<Person> list = new ArrayList<>();
    @Before
    public void constructor(){
        px = new Person("Adamn", "Aerlins", new AssignDate("2000-1-1").getDate());
        p0 = new Person("Adamn", "Aerlins", new AssignDate("2000-1-1").getDate());
        p1 = new Person("Bdamn", "Aerlins", new AssignDate("2000-1-1").getDate());
        p2 = new Person("Bdamn", "Berlins", new AssignDate("1999-1-1").getDate());
        p3 = new Person("Bdamn", "Berlins", new AssignDate("2000-1-1").getDate());
        p4 = new Person("Adamn", "Perlins", new AssignDate("1234-2-20").getDate());
        p5 = new Person("Adamn", "Perlins", new AssignDate("0567-2-20").getDate());
        p6 = new Person("Bdamn", "Perlins", new AssignDate("567-2-20").getDate());
        p7 = new Person("Adamn", "Perlins", new AssignDate("0089-2-20").getDate());
        p8 = new Person("Cdamn", "Perlins", new AssignDate("89-2-20").getDate());
        p9 = new Person("Adamn", "Perlins", new AssignDate("0009-2-20").getDate());
        p10 = new Person("Ddamn", "Perlins", new AssignDate("9-2-20").getDate());
        p11= new Person("February", "Check", new AssignDate( "2000-2-29").getDate());
        list.add(px);
        list.add(p0);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p7);
        list.add(p8);
        list.add(p9);
        list.add(p10);
        list.add(p11);
    }

    @Test
    public void parse(){
        Assert.assertEquals(InputParser.getPerson(),list);
    }

    @Test
    public void check(){
        String error0 = "February Echeck 2000-2-30";
        String error1 = "F1234name Check 2000-1-1";
        String error2 = "Fsurname Check12313 2000-1-1";
        String error3 = "Date Check 2022-01-01";
        Assert.assertEquals(null, InputParser.check(error0));
        Assert.assertEquals(null, InputParser.check(error1));
        Assert.assertEquals(null, InputParser.check(error2));
        Assert.assertEquals(null, InputParser.check(error3));
    }
    @Test (expected = NoSuchFileException.class)
    public void errorPath() throws IOException {
        String path = "src/eu/glowacki/utp/assignment04/thisFileDoesNotExist.txt";
        File file = new File(path);
        InputParser.parse(file);
    }
}
