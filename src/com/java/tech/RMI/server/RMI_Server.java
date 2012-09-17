package com.java.tech.RMI.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class RMI_Server {
    public static void main(String[] args) throws NotBoundException, IOException {
        try {
            LocateRegistry.createRegistry(9999);
            Naming.bind("rmi://localhost:9999/remoteBusiness", new BusinessImpl());
            Business b = (Business) Naming.lookup("rmi://localhost:9999/remoteBusiness");
            System.out.println(b.runBusiness());
            System.in.read();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}


