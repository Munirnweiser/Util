package com.java.tech.RMI.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class RMI_Server {
    public static void main(String[] args) throws NotBoundException {
        try {
            LocateRegistry.createRegistry(9999);
            Naming.bind("rmi://localhost:9999/remoteBusiness", new BusinessImpl());
            Business b = (Business) Naming.lookup("rmi://localhost:9999/remoteBusiness");
            System.out.println(b.runBusiness());
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


