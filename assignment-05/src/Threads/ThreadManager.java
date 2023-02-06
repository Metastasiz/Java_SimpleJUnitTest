package Threads;

import Task.TaskQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ThreadManager{
    public static <T extends MyThreads> List<T> createThreads(
            TaskQueue q, int count, Function<TaskQueue,T> c
    ){
        List<T> out = new ArrayList<>();
        for (int i = 0; i < count; i++)out.add(c.apply(q));
        return out;
    }
    public static List<Service> getServiceThreads(TaskQueue q, int count){
        return createThreads(q,count,Service::new);
    }
    public static List<Requestor> getRequestorThreads(TaskQueue q, int count){
        return createThreads(q,count,Requestor::new);
    }
}
