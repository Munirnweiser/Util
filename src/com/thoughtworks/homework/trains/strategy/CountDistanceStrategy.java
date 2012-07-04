package com.thoughtworks.homework.trains.strategy;

import com.thoughtworks.homework.trains.model.INode;

public class CountDistanceStrategy implements IRouteStrategy{
    public static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";
    @Override
    public String execute(Context context) {
        INode currNode = null;
        Integer wholeDistance = new Integer(0);
        for (INode n : context.getNodeList()) {
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
