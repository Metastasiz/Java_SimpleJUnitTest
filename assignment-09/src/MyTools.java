import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyTools {
    public static int myRandom(int lower, int upper){
        int out = (int)(Math.random()*(1+upper-lower))+lower;
        return out;
    }
    //
    private static ArrayList<String> myStringL = new ArrayList<>();
    public static void addStringL(String a){myStringL.add(a);}
    public static void addStringL(int a){myStringL.add(Integer.toString(a));}
    public static ArrayList<String> getStringL(){return myStringL;}
    //
    public class InnerCounter{
        private int counter;
        InnerCounter(int init){counter = init;}
        public synchronized int add(int a){
            counter +=a;
            return counter-a;
        }
    }
    public static class OuterCounter{
        private static int counter = 0;
        public synchronized static int getAssign(){return counter++;}
    }
    //
    public static class RandString {
        private String a;
        private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final String lower = upper.toLowerCase(Locale.ROOT);
        private static final String digits = "0123456789";
        private static final String all = upper+lower+digits;
        private static final String c = upper+lower;
        //
        public static String getString(int min, int max, String type){
            if (min > max)return null;
            boolean bMax = false, bChar = false, bName = false;
            if (type.toUpperCase(Locale.ROOT).contains("X"))bMax=true;
            if (type.toUpperCase(Locale.ROOT).contains("C"))bChar=true;
            if (type.toUpperCase(Locale.ROOT).contains("N"))bName=true;
            String out = "";
            int realMax = max;
            if (!bMax)realMax = myRandom(min+1,max);
            for (int i = min; i < realMax; i++){
                if (bChar)out += c.charAt(myRandom(0,c.length()-1));
                else out += all.charAt(myRandom(0, all.length()-1));
            }
            if (bName)out=out.substring(0,1).toUpperCase(Locale.ROOT)+out.substring(1).toLowerCase(Locale.ROOT);
            return out;
        }
    }
    public static class MyDate {
        final static String inputSepFormat = "-";
        public static Date getDate(int year, int month, int day){
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
            return cal.getTime();
        }
        public static Date getDate(String dob){
            String[] tmpArr = dob.split(inputSepFormat);
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
            return cal.getTime();
        }
        public static Date randThis(){
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, myRandom(1,cal.get(Calendar.YEAR)-18));
            cal.set(Calendar.MONTH, myRandom(1,12-1));
            cal.set(Calendar.DAY_OF_MONTH, myRandom(1,28));
            //
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            //
            return cal.getTime();
        }
        public static Date randThis(int lower, int upper){
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, myRandom(lower,upper));
            cal.set(Calendar.MONTH, myRandom(1,12-1));
            cal.set(Calendar.DAY_OF_MONTH, myRandom(1,28));
            //
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            //
            return cal.getTime();
        }
    }
    public static class DateValidator {
        private String dateFormat;
        public DateValidator(String dateFormat) {
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
    public static void main(String[] arg){
        int a = OuterCounter.getAssign();
        int b = OuterCounter.getAssign();
        //System.out.println(a);
        //System.out.println(b);
        System.out.println(RandString.getString(0,10,"xcn"));
        System.out.println(RandString.getString(0,10,"xc"));
        System.out.println(RandString.getString(0,10,"xn"));
        System.out.println(RandString.getString(0,10,"cn"));
        System.out.println(RandString.getString(0,10,"x"));
        System.out.println(RandString.getString(0,10,"c"));
        System.out.println(RandString.getString(0,10,"n"));
        System.out.println(RandString.getString(0,10,""));
    }

}
