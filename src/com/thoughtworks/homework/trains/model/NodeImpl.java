package com.thoughtworks.homework.trains.model;

import java.util.HashMap;
import java.util.HashSet;
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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Node){
            Node node = (Node) obj;
            return this.name.equals(node.getName());
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
