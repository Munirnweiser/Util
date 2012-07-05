package com.thoughtworks.homework.trains.strategy;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.homework.trains.TrainsFactory;
import com.thoughtworks.homework.trains.model.INode;
import com.thoughtworks.homework.trains.model.IRoute;

public class CountNoCircleRoutesBetweenTwoNodeStrategy implements ICountRouteStrategy {

    private List<IRoute> list;
    @Override
    public List<IRoute> countRoutes(IContext context) {
        List<String> nodes = context.getNodeNames();
        if (nodes.size() != 2)
            return null;
        INode startNode = TrainsFactory.getNode(nodes.get(0));
        INode endNode = TrainsFactory.getNode(nodes.get(1));
        list = new ArrayList<IRoute>();
        getNoCircleRoutes(startNode, endNode, null);
        return list;
    }

    /*
     * get routes that have no circle(no repeating node,such as A-B-C) 
     * or only have one circle(when startNode is also endNode, such as C-D-C)
     */
    private void getNoCircleRoutes(INode startNode, INode endNode, IRoute currRoute) {
        if (currRoute == null) {
            currRoute = TrainsFactory.getRoute(startNode.getName());
        } 
        for (INode node : startNode.getThroughNodes()) {
            //get independent route object
            IRoute tempRoute = TrainsFactory.getRoute(currRoute.getNodeNames());
            if (node == endNode){
                tempRoute.addNode(endNode.getName());
                list.add(tempRoute);
            }
            //no repeating node, then do the recursion
            else if (!tempRoute.contains(node.getName())){
                tempRoute.addNode(node.getName());
                getNoCircleRoutes(node, endNode, tempRoute);
            }
        }
    }
}
