package com.thoughtworks.homework.trains.strategy;

import java.util.List;

import com.thoughtworks.homework.trains.model.INode;

public class CountRoutesNumWithExactlyStopStrategy implements ICountRouteStrategy{
    @Override
    public String execute(ContextImpl context) {
        List<INode> nodes = context.getNodeList();
        if (nodes.size() != 2)return null;
        INode startNode = nodes.get(0);
        INode endNode = nodes.get(1);
        Integer stopNum = (Integer)context.getAttribute("exactlyStopNum");
        int routesNum = 0;
        routesNum = findRoutesNumWithExactlyStop(startNode,endNode,stopNum);
        return String.valueOf(routesNum);
    }
    
    private int findRoutesNumWithExactlyStop(INode startNode,INode endNode, int limitStop){
        int routeNum = 0;
        if (limitStop > 0){
            for (INode node : startNode.getThroughNodes()){
                //recursively find next level's node for limitStop times
                if (limitStop > 1){
                    routeNum += findRoutesNumWithExactlyStop(node, endNode,limitStop - 1);
                } else if (node.equals(endNode)){
                    routeNum++;
                    break;//no duplicate node
                }
            }
        }
        return routeNum;
    }

}
