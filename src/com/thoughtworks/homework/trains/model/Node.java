package com.thoughtworks.homework.trains.model;

import java.util.Set;

public interface Node {
    public void setName(String name);
    public String getName();
    public void setRoute(Node n, int distance);
    public Integer getDistance(Node n);
    public Set<Node> getThroughNodes();
}
