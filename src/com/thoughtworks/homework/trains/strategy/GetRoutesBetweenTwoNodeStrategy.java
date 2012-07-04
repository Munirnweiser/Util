package com.thoughtworks.homework.trains.strategy;

import java.util.List;

import com.thoughtworks.homework.trains.model.INode;
import com.thoughtworks.homework.trains.model.IRoute;
import com.thoughtworks.homework.trains.model.TrainsFactory;

public class GetRoutesBetweenTwoNodeStrategy implements ICountRouteStrategy{

    @Override
    public List<IRoute> countRoutes(IContext context) {
        List<String> nodes = context.getNodeNames();
        if (nodes.size() != 2)return null;
        INode startNode = TrainsFactory.getNode(nodes.get(0));
        INode endNode = TrainsFactory.getNode(nodes.get(1));
        return getRoutes(startNode, endNode);
    }
    private List<IRoute> getRoutes(INode startNode, INode endNode){
        for (INode node : startNode.getThroughNodes()){
            if (node == endNode){
            } else {
                
            }
        }
        return null;
    }
}
