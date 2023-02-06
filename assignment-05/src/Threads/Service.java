package Threads;

import Task.*;

public class Service extends MyThreads {
    private static int _counter = 0;
    private int ID;
    public Service(TaskQueue a){
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
        if (!((out = getTask()) instanceof TaskIn)){
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
        System.out.println("running ser");
        while(true){
            if (checkQueue()){
                TaskIn tmp = (TaskIn)consume(); //Consume TaskIn
                sleep();
                //
                if (tmp != null){
                    TaskOut tmp2 = new TaskOut(tmp);
                    send(tmp2);
                    q.addTask(tmp2);
                    sleep();
                }
            }
        }
    }
}
