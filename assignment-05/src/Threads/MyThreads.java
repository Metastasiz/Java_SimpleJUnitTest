package Threads;

import Task.Task;
import Task.TaskQueue;
import com.sun.source.tree.SynchronizedTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MyThreads implements Runnable{
    private final static int _sleep = 2000;
    //
    protected final TaskQueue q;
    MyThreads(TaskQueue q){
        this.q=q;
    }
    public void send(Task t){
        System.out.println(this + " has sent " + t);
    }
    public synchronized boolean checkQueue(){return false;}
    public synchronized Task getTask(){return null;}
    public synchronized Task consume(){return null;}
    protected void sleep() {
        try {
            Thread.sleep(_sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
    }
}
