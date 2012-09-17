package com.java.tech.JMX;

import java.lang.management.ManagementFactory;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;




public class MyMBeanAgent {
    private MBeanServer mbs;
    private static final String rmiURL = "rmi://localhost:9988/test11";
    private static final String jmxServiceURL = "service:jmx:rmi://localhost:9998/jndi/" + rmiURL;
    
    public MyMBeanAgent(){
        mbs = ManagementFactory.getPlatformMBeanServer();
        My myMBean= new My();
        try {
            //rmi
            LocateRegistry.createRegistry(9988);
            
            //This will bind to "rmi://localhost:9988/test11"
            JMXServiceURL jmxUrl = new JMXServiceURL(jmxServiceURL);
            
            //start a jmx connector server
            JMXConnectorServer server = JMXConnectorServerFactory.newJMXConnectorServer(jmxUrl, null, mbs);
            server.start();
            System.out.println("Binding " + Naming.lookup(rmiURL) + " to " + rmiURL);
            
            ObjectName myMB = new ObjectName("MyMBeanAgent1:name=test");
            mbs.registerMBean(myMBean, myMB);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
    }
    
    public static void main(String[] args) {
        try {
            MyMBeanAgent m = new MyMBeanAgent();
            
            System.out.println("Starting.....");
            System.out.println("Please use jconsole to connect \"" + jmxServiceURL + "\"");
            System.in.read();
            //use jconsole to remote connect "service:jmx:rmi://localhost:9998/jndi/rmi://localhost:9988/test11"
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
