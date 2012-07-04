package com.thoughtworks.homework.trains.model;

import java.util.Map;
import java.util.Set;

public class NodeImpl implements INode {
    private String name;
    private Map<INode, Integer> routeMap;

    NodeImpl(String name, Map<INode, Integer> routeMap) {
        this.name = name;
        this.routeMap = routeMap;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setRoute(INode n, int distance) {
        routeMap.put(n, distance);
    }

    @Override
    public Integer getDistance(INode n) {
        return routeMap.get(n);
    }

    @Override
    public Set<INode> getThroughNodes() {
        return routeMap.keySet();
    }
    
    @Override
    public boolean hasThroughRouteTo(INode n) {
        return routeMap.containsKey(n);
    }
}
