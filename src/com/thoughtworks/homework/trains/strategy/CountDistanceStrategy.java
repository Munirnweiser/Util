package com.thoughtworks.homework.trains.strategy;

import com.thoughtworks.homework.trains.model.Node;

public class CountDistanceStrategy implements RouteStrategy{
    public static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";
    @Override
    public String execute(Node... nodes) {
        Node currNode = null;
        Integer wholeDistance = new Integer(0);
        for (Node n : nodes) {
            if (currNode == null) {
                currNode = n;
            } else {
                Integer distance = currNode.getDistance(n);
                if (distance == null) {
                    return NO_SUCH_ROUTE;
                } else {
                    wholeDistance += distance;
                    currNode = n;
                }
            }
        }
        return String.valueOf(wholeDistance);
    }

}
