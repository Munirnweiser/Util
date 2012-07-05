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
        getRoutes(startNode, endNode, null);
        return list;
    }

    private void getRoutes(INode startNode, INode endNode, IRoute currRoute) {
        if (currRoute == null) {
            currRoute = TrainsFactory.getRoute(startNode.getName());
        } 
        for (INode node : startNode.getThroughNodes()) {
            IRoute tempRoute = TrainsFactory.getRoute(currRoute.getNodeNames());
            if (node == endNode){
                tempRoute.addNode(endNode.getName());
                list.add(tempRoute);
            }
            if (!tempRoute.contains(node.getName())){
                tempRoute.addNode(node.getName());
                getRoutes(node, endNode, tempRoute);
            }
        }
    }
}
