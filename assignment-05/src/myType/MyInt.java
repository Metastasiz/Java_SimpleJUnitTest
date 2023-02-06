package myType;

import java.util.Random;

public class MyInt implements MyType {
    private int a;
    public MyInt(){a = new Random().nextInt(1000);}
    public MyInt(int set){a = set;}
    public Object getValue(){return a;}
    public static void main(String[] arg){
        System.out.println(new MyInt().getValue());
        System.out.println(new MyInt().getValue());
        System.out.println(new MyInt().getValue());
    }
}
