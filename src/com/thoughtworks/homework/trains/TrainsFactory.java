package com.thoughtworks.homework.trains;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.homework.trains.exception.UnSupportRouteException;
import com.thoughtworks.homework.trains.model.INode;
import com.thoughtworks.homework.trains.model.IRoute;
import com.thoughtworks.homework.trains.model.NodeImpl;
import com.thoughtworks.homework.trains.model.RouteImpl;
import com.thoughtworks.homework.trains.strategy.ContextImpl;
import com.thoughtworks.homework.trains.strategy.IContext;

public class TrainsFactory {
    private static Map<String, INode> nodesMap = new HashMap<String, INode>();

    private TrainsFactory() {
    }

    //Make sure get the same node by String name
    public static INode getNode(String name) {
        INode node = nodesMap.get(name);
        if (node == null) {
            node = new NodeImpl(name, new HashMap<INode, Integer>());
            nodesMap.put(name, node);
        }
        return node;
    }

    public static IRoute getRoute(String... nodeNames) {
        if (nodeNames == null || nodeNames.length == 0) {
            return null;
        }
        IRoute route = null;
        INode[] nodes = new INode[nodeNames.length];
        for (int i = 0; i < nodeNames.length; i++) {
            nodes[i] = getNode(nodeNames[i]);
        }
        try {
          //Make sure get independent route object
            route = new RouteImpl(nodes);
        } catch (UnSupportRouteException e) {
            return null;
        }
        return route;
    }
    
    public static IContext getContext(String ... nodeNames){
        return new ContextImpl(nodeNames);
    }
}
