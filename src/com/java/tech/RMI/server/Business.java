package com.java.tech.RMI.server;

import java.io.Serializable;
import java.rmi.Remote;

public interface Business extends Remote,Serializable{
    String runBusiness();
    Data getData();
}
