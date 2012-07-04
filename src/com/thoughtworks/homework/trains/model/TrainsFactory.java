package com.thoughtworks.homework.trains.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.homework.trains.exception.UnSupportRouteException;

public class TrainsFactory {
    private static Map<String, INode> nodesMap = new HashMap<String, INode>();
    private static Map<String, IRoute> routesMap = new HashMap<String, IRoute>();
    
    private TrainsFactory(){}
    public static INode getNode(String name) {
        INode node = nodesMap.get(name);
        if (node == null){
            node = new NodeImpl(name, new HashMap<INode, Integer>());
            nodesMap.put(name, node);
        }
        return node;
    }

    public static IRoute getRoute(String... nodeNames) {
        if (nodeNames == null || nodeNames.length == 0){
            return null;
        }
        String routeName = Arrays.toString(nodeNames);
        IRoute route = routesMap.get(routeName);
        if (route == null){
            INode[] nodes = new INode[nodeNames.length];
            for (int i = 0; i < nodeNames.length; i++) {
                nodes[i] = getNode(nodeNames[i]);
            }
            try {
                route = new RouteImpl(nodes);
            } catch (UnSupportRouteException e) {
                return null;
            }
            routesMap.put(routeName,route);
        }
        return route;
    }
    public static void main(String[] args) {
        getRoute();
    }
}
