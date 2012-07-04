package com.thoughtworks.homework.trains.model;

import java.util.HashMap;

public class NodeFactory {
    public static INode createNode(String name) {
        return new NodeImpl(name, new HashMap<INode, Integer>());
    }
}
