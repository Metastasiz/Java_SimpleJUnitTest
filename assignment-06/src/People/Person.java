package People;

import Groups.Subject;

import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Person implements Comparable<Person> {
    public final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy/MM/dd");
    static Map<Date, Integer> _dateCounter = new HashMap<>();
    private final String fname, lname;
    private String ssn;
    private final Date dob;
    private final Nation nation;
    protected Person(Nation n, String b, String c, Date date){
        nation = n; fname = b; lname = c; dob = date;
        INIT();
        thisList.add(this);
    }
    //
    private static List<Person> thisList = new ArrayList<>();
    public static List<Person> getAll(){return thisList;}
    public static Person getThisRand(){if(getAll().size() == 0)return null;return getAll().get(new Random().nextInt(getAll().size()));}
    public static void CLEAR(){thisList = new ArrayList<>();}
    //
    private void INIT(){
        if (!_dateCounter.containsKey(dob)){
            _dateCounter.put(dob, 0);
        } else {
            _dateCounter.put(dob, (_dateCounter.get(dob)+1));
        }
        //
        Calendar c = Calendar.getInstance();
        c.setTime(dob);
        //
        String tmp2 = "";
        tmp2 += c.get(Calendar.YEAR);
        tmp2 += dob.getMonth()+1;
        tmp2 += dob.getDate();
        String tmp3 = Integer.toString(_dateCounter.get(dob));
        for (int i = 5; i > tmp3.length(); i--){tmp2 += "0";}
        tmp2 += tmp3;
        ssn = tmp2;
    }
    public String getSsn(){return ssn;}
    public String getFname(){return fname;}
    public String getLname(){return lname;}
    public Nation getNation(){return nation;}
    public Date getDob(){return dob;}
    public int getAge(){
        Date current = new Date();
        Calendar c = Calendar.getInstance();

        c.setTime(current);
        int curY = c.get(Calendar.YEAR);

        c.setTime(dob);
        int bthY = c.get(Calendar.YEAR);

        return curY - bthY;
    }
    @Override
    public String toString(){
        return "\n"+getSsn() + " " + getLname().charAt(0) + ". " + fname + ", is " + getNation() + " " + timeFormat.format(getDob()) ;
    }
    public static final Comparator<Person> comp = Comparator
            .comparing(Person::getLname)
            .thenComparing(Person::getFname)
            .thenComparing(Person::getAge)
            .thenComparing(Person::getSsn)
            .thenComparing(Person::getNation);
    @Override
    public int compareTo(Person in){
        return comp.compare(this,in);
    }
    public static void main(String[] arg){
    }
}
