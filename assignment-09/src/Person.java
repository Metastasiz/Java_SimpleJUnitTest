import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    //formats
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy/MM/dd");
    //tools
    private static MyTools.OuterCounter _assignCounter = new MyTools.OuterCounter();
    private int thisCounter;
    //info
    private String fname, lname;
    private Date dob;
    private Gender gender;
    private String pesel;
    //
    public Person(){
        INIT();
        randPerson();
        MyPESEL.addPerson(this);
    }
    private synchronized void INIT(){
        thisCounter = _assignCounter.getAssign();
    }
    private void randPerson(){
        fname=MyTools.RandString.getString(1,10,"cn");
        lname=MyTools.RandString.getString(1,10,"cn");
        gender=Gender.randThis();
        dob=MyTools.MyDate.randThis(1800,2299);
    }
    public synchronized void assignPesel(String a){pesel=a;}
    public String getPesel(){return pesel;}
    public Date getDOB(){return dob;}
    public Gender getGender(){return gender;}
    public int getThisCounter(){return thisCounter;}
    public static void main(String[] arg){
        Person a = new Person();
        Person b = new Person();
        System.out.println(a.getThisCounter());
        System.out.println(b.getThisCounter());
        System.out.println(b.getThisCounter());
    }
}
