package com.thoughtworks.homework.trains.model;

import java.util.Map;
import java.util.Set;

public class NodeImpl implements Node {
    private String name;
    private Map<Node, Integer> routeMap;

    public NodeImpl(String name, Map<Node, Integer> routeMap) {
        this.name = name;
        this.routeMap = routeMap;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setRoute(Node n, int distance) {
        routeMap.put(n, distance);
    }

    @Override
    public Integer getDistance(Node n) {
        return routeMap.get(n);
    }

    @Override
    public Set<Node> getThroughNodes() {
        return routeMap.keySet();
    }

}
