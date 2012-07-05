package com.thoughtworks.homework.trains.model;

public interface IRoute {
    String[] getNodeNames();

    int getStops();

    int getDistance();

    boolean addNode(String nodeName);

    boolean contains(String nodeName);

}
