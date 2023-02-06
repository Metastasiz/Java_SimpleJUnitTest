package Task;

import myType.MyInt;
import myType.MyString;
import myType.MyType;

import java.util.Random;

public class TaskIn  extends Task{
    private final MyType value1;
    private final MyType value2;
    public TaskIn(){
        super();
        if (new Random().nextInt(2) < 1){
            value1 = new MyInt();
            value2 = new MyInt();
        } else {
            value1 = new MyString();
            value2 = new MyString();
        }
    }
    public MyType getV1(){return value1;}
    public MyType getV2(){return value2;}

    public String toString(){
        return super.toString() + " = {" + value1.getValue() + ", " + value2.getValue() + "}";
    }
}
