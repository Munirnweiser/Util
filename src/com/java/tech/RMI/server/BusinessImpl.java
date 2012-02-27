package com.java.tech.RMI.server;


public class BusinessImpl implements Business{

    @Override
    public String runBusiness() {
        return "Running business.....";
    }

    @Override
    public Data getData() {
        Data d = new DataImpl();
        d.setCompanyName("Augmentum");
        d.setAddress("Shanghai");
        return d;
    }

}
