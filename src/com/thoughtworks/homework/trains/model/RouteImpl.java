package com.thoughtworks.homework.trains.model;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.homework.trains.TrainsFactory;
import com.thoughtworks.homework.trains.exception.UnSupportRouteException;

public class RouteImpl implements IRoute {
    private List<INode> nodeList = new ArrayList<INode>();

    public RouteImpl(INode... nodes) throws UnSupportRouteException {
        for (INode node : nodes) {
            if (!addNode(node.getName())) {
                throw new UnSupportRouteException();
            }
        }
    }

    @Override
    public String[] getNodeNames() {
        String[] nodeNames = new String[nodeList.size()];
        for (int i = 0; i < nodeList.size(); i++){
            nodeNames[i] = nodeList.get(i).getName();
        }
        return nodeNames;
    }

    @Override
    public int getStops() {
        return nodeList.size() - 1;
    }

    @Override
    public int getDistance() {
        int distance = 0;
        for (int i = 0; i < nodeList.size() - 1; i++) {
            distance += nodeList.get(i).getDistance(nodeList.get(i + 1));
        }
        return distance;
    }

    @Override
    public boolean addNode(String nodeName) {
        INode node = TrainsFactory.getNode(nodeName);
        if (nodeList.size() == 0) {
            nodeList.add(node);
        } else {
            INode lastNode = nodeList.get(nodeList.size() - 1);
            if (lastNode.hasThroughRouteTo(node)) {
                nodeList.add(node);
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(String nodeName) {
        return nodeList.contains(TrainsFactory.getNode(nodeName));
    }

}
