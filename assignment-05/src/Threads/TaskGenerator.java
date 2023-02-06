package Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Task.*;
import main.Main;

public class TaskGenerator implements Runnable{
    private final static int _sleepBound = 1000;
    public final static TaskGenerator _generator = new TaskGenerator();
    //
    private int size = Main._serviceCount;
    private final List<TaskIn> taskGen;
    public TaskGenerator() {
        taskGen = new ArrayList<>();
    }
    public synchronized List<TaskIn> getTaskGen(){return taskGen;}
    public synchronized TaskIn getTask(){
        TaskIn out = null;
        if (taskGen.size() > 0) {
            out = taskGen.get(0);
            taskGen.remove(0);
        }
        return out;
    }
    protected void sleep() {
        try {
            Thread.sleep(new Random().nextInt(_sleepBound));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized int getSize(){return taskGen.size();}
    public synchronized boolean checkTask(){
        return taskGen.size() > 0;
    }

    @Override
    public void run() {
        System.out.println("running gen");
        while(true){
            if (getSize() < size){
                TaskIn tmp = new TaskIn();
                System.out.println("Generated "+ tmp);
                taskGen.add(tmp);
                sleep();
            }
        }
    }
}
