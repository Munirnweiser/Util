package com.java.tech.JMX;

public class My implements MyMBean{

    private String accountName;
    public String getAccountName() {
        return accountName;
    }

    public void playDOTA() {
        System.out.println("I'm playing DOTA with the account name: " + getAccountName());
    }

    public void setAccountName(String name) {
        accountName = name;
    }

}
