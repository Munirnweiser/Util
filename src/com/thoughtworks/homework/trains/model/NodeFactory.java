package com.thoughtworks.homework.trains.model;

import java.util.HashMap;

public class NodeFactory {
    public static Node createNode(String name) {
        return new NodeImpl(name, new HashMap<Node, Integer>());
    }
}
