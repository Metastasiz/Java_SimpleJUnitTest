package eu.glowacki.utp.assignment03.employee;

import java.util.Calendar;
import java.util.Date;

public class AssignDate {
    public Date date;
    public AssignDate(int a, int b, int c){
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, a);
        cal.set(Calendar.MONTH, b);
        cal.set(Calendar.YEAR, c);
        date = cal.getTime();
    }
    public Date getDate(){return date;}
}
