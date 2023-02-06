package Task;

import java.util.Random;

public enum TaskPriority {
    High("High priority"), Med("Medium priority"), Low("Low priority");
    private final String name;
    TaskPriority(String a){name=a;}
    public static TaskPriority getRandomPriority(){
        TaskPriority[] out = TaskPriority.values();
        Random tmp = new Random();
        int tmp2 = tmp.nextInt(out.length);
        return out[tmp2];
    }
    public String toString(){return name;}
}

