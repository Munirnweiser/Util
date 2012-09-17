package com.java.tech.RMI.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import com.java.tech.RMI.server.Business;

public class RMI_Client implements Remote{
    public static void main(String[] args) {
        try {
            //Business接口与Server上的一样（由server提供给client并放在相应package）
            Business b = (Business) Naming.lookup("rmi://localhost:9999/remoteBusiness");
            System.out.println(b.runBusiness());
            System.out.println(b.getData().getAddress());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
