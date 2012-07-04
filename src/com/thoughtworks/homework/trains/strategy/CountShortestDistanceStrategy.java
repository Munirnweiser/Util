package com.thoughtworks.homework.trains.strategy;

import java.util.List;

import com.thoughtworks.homework.trains.model.INode;

public class CountShortestDistanceStrategy implements IRouteStrategy{
    @Override
    public String execute(Context context) {
        List<INode> nodes = context.getNodeList();
        if (nodes.size() != 2)return null;
        INode startNode = nodes.get(0);
        INode endNode = nodes.get(1);
        int shortestDistance = 0;
        shortestDistance = countShortestDistance(startNode, endNode, 0, 0);
        return String.valueOf(shortestDistance);
    }
    /**
     * 
     * @param startNode
     * @param endNode
     * @param currDistance, store the current distance of route path in recursion 
     * @return shortest distance between startNode and endNode
     */
    private int countShortestDistance(INode startNode, INode endNode, int currDistance, int shortestDistance){
        for (INode node : startNode.getThroughNodes()){
            int temp = currDistance + startNode.getDistance(node);
            if (shortestDistance == 0 || temp < shortestDistance){
                if (node.equals(endNode)){
                    shortestDistance = currDistance;
                } else {
                    countShortestDistance(node, endNode, currDistance, shortestDistance);
                } 
            }
        }
        return shortestDistance;
    }
}
