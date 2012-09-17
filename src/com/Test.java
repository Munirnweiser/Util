package com;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "testName", namespace = "testNamespace")
public class Test<Integer> implements Serializable {
    protected Test() throws IOException {
        throw new IOException();
    }
    public static class IRunnable implements Runnable {
        final List<String> list = new ArrayList<String>();
        private ScheduledFuture future;
        public void setFuture(ScheduledFuture future){
            this.future = future;
        }
        private ScheduledExecutorService pool;
        public void setScheduledExecutorService(ScheduledExecutorService pool){
            this.pool = pool;
        }
        @Override
        public void run(){
            if (list.size() < 5){
                list.add("1");
                System.out.println(list.size() + "..add");
            } else {
                future.cancel(true);
                System.out.println("cancel....");
                pool.shutdown();
            }
            System.out.println(11111);
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, JAXBException, InterruptedException, ExecutionException {
        List<String> list = null;
    }
    
    
}
