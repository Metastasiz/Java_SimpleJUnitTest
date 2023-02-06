package People;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AssignDate {
    public static final int RAND = 0;
    public Date date;
    final static String regDateSep = "-";
    public AssignDate(int year, int month, int day){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        //
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        //
        date = cal.getTime();
    }
    public AssignDate(int a){
        switch (a){
            case 0:
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, new Random().nextInt(cal.get(Calendar.YEAR)));
                cal.set(Calendar.MONTH, new Random().nextInt(12));
                cal.set(Calendar.DAY_OF_MONTH, new Random().nextInt(28));
                //
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                //
                date = cal.getTime();
        }
    }
    public AssignDate(){
        Calendar cal = Calendar.getInstance();
        //
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        //
        date = cal.getTime();
    }
    public AssignDate(String dob){
        String[] tmpArr = dob.split(regDateSep);
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, Integer.valueOf(tmpArr[0]));
        cal.set(Calendar.MONTH, Integer.valueOf(tmpArr[1])-1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(tmpArr[2]));
        //
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        //
        date = cal.getTime();
    }
    public Date getDate(){return date;}
}
