package Threads;

import Task.*;

public class Requestor extends MyThreads {
    private static int _counter = 0;
    private int ID;
    public Requestor(TaskQueue a){
        super(a);
        ID = _counter++;
    }
    @Override
    public synchronized boolean checkQueue(){return q.checkTask();}
    @Override
    public synchronized Task getTask(){return q.getTask();}
    @Override
    public synchronized Task consume(){
        Task out;
        if (!((out = getTask()) instanceof TaskOut)){
            q.addTask(out);
            return null;
        }
        System.out.println(this + " has received " + out);
        return out;
    }
    @Override
    public String toString(){return getClass().getName() + " " + ID;}
    @Override
    public void run() {
        System.out.println("running req");
        while(true){
            if (TaskGenerator._generator.checkTask()){
                TaskIn tmp = TaskGenerator._generator.getTask();
                send(tmp);
                q.addTask(tmp);
                sleep();
            }
            if (checkQueue()){
                consume(); //Consume TaskOut
                sleep();
            }

        }
    }
}
