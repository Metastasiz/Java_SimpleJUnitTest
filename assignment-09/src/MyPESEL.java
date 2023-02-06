import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyPESEL {
    private int peselNo;
    public int getPesel(){return peselNo;}
    private static final Map<Date,ArrayList<Person>> _peselDB = new HashMap<>();
    private static final Map<Date,ArrayList<String>> _dateDB = new HashMap<>();
    //
    private static final String DIGIT_PAT = "[0-9]";
    private static final String PESEL_PAT =
            "^" +
            DIGIT_PAT + "{2}" +
            DIGIT_PAT + "{2}" +
            DIGIT_PAT + "{2}" +

            DIGIT_PAT + "{3}" +
            DIGIT_PAT + "{1}" +

            DIGIT_PAT + "{1}" +

            "$";
    private static final Pattern PESEL_REGEX = Pattern.compile(PESEL_PAT);
    //
    private final String value;
    private final Date dob;
    private final Gender gender;
    private static final int[] _lastDigit = {1,3,7,9,1,3,7,9,1,3};
    public MyPESEL(String v) throws PESELexception {
        validateRegex(v,true);
        validateLastDigit(v,true);
        this.value = v;
        dob = findDate(v);
        gender = findGender(v);
    }
    public String getValue(){return value;}
    public Date getDOB(){return dob;}
    public Gender getGender(){return gender;}
    public static boolean validateRegex(String a, boolean t) throws PESELexception {
        Matcher match = PESEL_REGEX.matcher(a);
        boolean m = match.matches();
        if (!m){
            if (t)
                throw new PESELexception("invalid PESEL regex: " +a);
        }
        return m;
    }
    public static boolean validateLastDigit(String a, boolean t) throws PESELexception{
        String tmp = a.substring(0,a.length()-1);
        int mod = 0;
        for (int i = 0; i < tmp.length(); i++){
            int modifier = _lastDigit[i];
            int add = Integer.valueOf(tmp.substring(i,i+1))*modifier;
            mod += add;
        }
        boolean d = 10-(mod%10)==Integer.valueOf(a.substring(a.length()-1));
        if (!d){
            if (t)
                throw new PESELexception("invalid PESEL last digit: " +a);
        }
        return d;
    }
    public static Date findDate(String a) throws PESELexception{
        if ((Integer.valueOf(a.substring(2,4))%20-1)>11) throw new PESELexception("invalid PESEL month: "+a);
        if ((Integer.valueOf(a.substring(4,6)))>31) throw new PESELexception("invalid PESEL date: "+a);
        String date = "";
        if (Integer.valueOf(a.substring(2,4))<20)date+="19";
        else if (Integer.valueOf(a.substring(2,4))<40)date+="20";
        else if (Integer.valueOf(a.substring(2,4))<60)date+="21";
        else if (Integer.valueOf(a.substring(2,4))<80)date+="22";
        else if (Integer.valueOf(a.substring(2,4))<100)date+="18";
        date += a.substring(0,2) + "-";
        date += Integer.valueOf(a.substring(2,4))%20 + "-";
        date += a.substring(4,6);
        return MyTools.MyDate.getDate(date);
    }
    public static Gender findGender(String a) {
        if (Integer.valueOf(a.substring(9,10))%2==0)return Gender.F;
        else return Gender.M;
    }
    public synchronized static Map<Date,ArrayList<Person>> getPeselDB(){return _peselDB;}
    public synchronized static void addPerson(Person person){
        if (!getPeselDB().containsKey(person.getDOB())){
            getPeselDB().put(person.getDOB(),new ArrayList(Arrays.asList(person)));
            //assign pesel
        } else {
            getPeselDB().get(person.getDOB()).add(person);
            //assign pesel
        }
        person.assignPesel(assignPesel(person));
    }
    public static String assignPesel(Person a){
        Calendar c = Calendar.getInstance();
        c.setTime(a.getDOB());
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DATE);
        System.out.println(a.getDOB());
        System.out.println(year+" "+ month + " " + day);
        String out = Integer.toString(year).substring(2);
        if (1800 <= year && year <= 1899){out += (month+80);}
        else if (1900 <= year && year <= 1999){if(month<10)out+="0";out += (month+0);}
        else if (2000 <= year && year <= 2099){out += (month+20);}
        else if (2100 <= year && year <= 2199){out += (month+40);}
        else if (2200 <= year && year <= 2299){out += (month+60);}
        else {throw new IndexOutOfBoundsException("Year is out of bounds");}
        if (day<10)out+="0";
        out += day;
        //
        int tmp = getPeselDB().get(a.getDOB()).size();
        int s = String.valueOf(tmp).length();
        String d3 = "";
        boolean pass = false;
        while(!pass) {
            d3 = "";
            for (int i = 0; i < (3 - s); i++)
                d3 += 0;
            d3 += tmp;
            if (!_dateDB.containsKey(a.getDOB())){
                _dateDB.put(a.getDOB(),new ArrayList(Arrays.asList(d3)));
                pass = true;
            } else if (!_dateDB.get(a.getDOB()).contains(d3)){
                _dateDB.get(a.getDOB()).add(d3);
                pass = true;
            }
        }
        out += d3;
        //
        if (a.getGender().equals("Male")){out += (MyTools.myRandom(0,4)*2)+1;}
        else {out += (MyTools.myRandom(0,4)*2);}
        //
        int mod = 0;
        for (int i = 0; i < out.length(); i++)
            mod += ((Integer.valueOf(out.substring(i,i+1))*((2*i)%10))+1)%10;
        out += mod%10;
        //
        return out;
    }
    public static void main(String[] arg) throws PESELexception {
        System.out.println(MyPESEL.validateRegex("97030112654",false));
        System.out.println(MyPESEL.validateLastDigit("97030112654",false));
        System.out.println(MyPESEL.findDate("97030112654"));
        System.out.println(MyPESEL.findGender("97030112654"));
    }
}
