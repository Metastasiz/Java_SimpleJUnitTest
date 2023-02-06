package eu.glowacki.utp.assignment01;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BankFrame {
    final static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    public String time;
    void INIT(){time = timeFormat.format(Calendar.getInstance().getTime());}
    String getTime(){return time;}
}