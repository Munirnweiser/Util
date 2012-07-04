package com.thoughtworks.homework.trains.model;

public interface IRoute {
    public String getPath();
    public int getStops();
    public int getDistance();
    public boolean addNode(INode node);
}
