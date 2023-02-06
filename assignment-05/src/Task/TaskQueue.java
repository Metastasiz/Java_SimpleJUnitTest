package Task;

import java.util.PriorityQueue;
import java.util.Queue;

public class TaskQueue {
    private final Queue<Task> q;
    public TaskQueue(){q = new PriorityQueue<Task>();}
    public Queue<Task> getQueue(){return q;}
    public synchronized boolean checkTask(){
        return q.size() > 0;
    }
    public synchronized Task getTask(){
        Task out = q.peek();
        if (out != null)out = q.poll();
        return out;
    }
    public synchronized void addTask(Task in){
        if (in == null)return;
        q.add(in);
    }
}
