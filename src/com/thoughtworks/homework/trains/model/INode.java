package com.thoughtworks.homework.trains.model;

import java.util.Set;

public interface INode {
    public String getName();
    public void setRoute(INode n, int distance);
    public Integer getDistance(INode n);
    public Set<INode> getThroughNodes();
    public boolean hasThroughRouteTo(INode n);
}
