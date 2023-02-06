package myType;

import java.util.Locale;
import java.util.Random;

public class MyString implements MyType {
    private String a;
    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase(Locale.ROOT);
    private static final String digits = "0123456789";
    private static final String[] chars = {upper,lower,digits};
    //
    public MyString(){
        a = randString(20);
    }
    public MyString(String set){a = set;}
    public String randString(int bound){
        String out = "";
        int l = new Random().nextInt(bound) + 1;
        for (int i = 0; i < l; i++) {
            int tmp = new Random().nextInt(chars.length);
            out += chars[tmp].charAt(new Random().nextInt(chars[tmp].length()));
        }
        return out;
    }
    public Object getValue(){return a;}
    public static void main(String[] arg){
        System.out.println(new MyString().getValue());
        System.out.println(new MyString().getValue());
        System.out.println(new MyString().getValue());
    }
}
