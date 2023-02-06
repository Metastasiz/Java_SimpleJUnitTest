package Task;

import myType.MyInt;
import myType.MyString;
import myType.MyType;

public class TaskOut extends Task{
    private final MyType result;
    public TaskOut(TaskIn in){
        super();
        result = compute(in);
    }
    public MyType compute(TaskIn in){
        if (in == null) return null;
        if (in.getV1() instanceof MyString){
            return new MyString(in.getV1().getValue() + " AND " + in.getV2().getValue());
        } else if (in.getV1() instanceof MyInt){
            return new MyInt((int)in.getV1().getValue() * (int)in.getV2().getValue());
        }
        return null;
    }
    public String toString(){return super.toString() + " = {" + result.getValue() + "}";}
}
