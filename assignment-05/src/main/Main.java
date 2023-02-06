package main;

import Task.TaskQueue;
import Threads.*;


import java.util.List;

public class Main {
    public static int _requestorCount = 3, _serviceCount = 4;
    public static void main(String[] arg){
        new Thread(TaskGenerator._generator).start();
        //
        TaskQueue q = new TaskQueue();
        List<Requestor> requestorThreads = ThreadManager.getRequestorThreads(q,_requestorCount);
        List<Service> serviceThreads = ThreadManager.getServiceThreads(q,_serviceCount);
        for (Requestor e : requestorThreads) new Thread(e).start();
        for (Service e : serviceThreads) new Thread(e).start();
    }
}
