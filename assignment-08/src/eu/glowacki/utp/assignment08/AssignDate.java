package eu.glowacki.utp.assignment08;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class AssignDate {
    final static String regDateSep = "-";
    public Date date;
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
class ValidatorDF {
    private String dateFormat;
    public ValidatorDF(String dateFormat) {
        this.dateFormat = dateFormat;
    }
    public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
