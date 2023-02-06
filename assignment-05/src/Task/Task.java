package Task;

import java.util.Date;

public class Task implements Comparable<Task>{
    public static int _counter = 0;
    //
    private int ID;
    private Date date;
    private TaskPriority priority;
    private synchronized static int assignID(){return _counter++;}
    //
    Task(){
        ID = assignID();
        date = new Date();
        priority = TaskPriority.getRandomPriority();
    }
    //
    public int getID(){return ID;}
    public Date getDate(){return date;}
    public TaskPriority getPriority() {
        return priority;
    }
    //
    @Override
    public final int compareTo(Task in){
        if (in == null) return -1;
        int out = getPriority().compareTo(in.getPriority());
        if (out != 0) return out;
        out = getDate().compareTo(in.getDate());
        if (out != 0) return out;
        return getID()-in.getID();
    }
    @Override
    public String toString(){
        return getPriority().toString() + "; " + getClass().getName() + "-ID:" + getID();
    }
}
