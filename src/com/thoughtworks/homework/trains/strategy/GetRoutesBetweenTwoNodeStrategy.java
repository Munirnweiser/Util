package com.thoughtworks.homework.trains.strategy;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.homework.trains.model.INode;
import com.thoughtworks.homework.trains.model.IRoute;
import com.thoughtworks.homework.trains.model.TrainsFactory;

public class GetRoutesBetweenTwoNodeStrategy implements IRouteStrategy{

    @Override
    public List<IRoute> execute(Context context) {
        List<INode> nodes = context.getNodeList();
        if (nodes.size() != 2)return null;
        INode startNode = nodes.get(0);
        INode endNode = nodes.get(1);
        return getRoutes(startNode, endNode);
    }
    private List<IRoute> getRoutes(INode startNode, INode endNode, List<IRoute> routes){
        IRoute route = new ;
        for (INode node : startNode.getThroughNodes()){
            if (node == endNode){
                route.
            } else {
                
            }
        }
        return null;
    }
}
