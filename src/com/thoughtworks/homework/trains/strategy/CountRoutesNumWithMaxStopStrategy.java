package com.thoughtworks.homework.trains.strategy;

import java.util.List;

import com.thoughtworks.homework.trains.model.INode;

public class CountRoutesNumWithMaxStopStrategy implements ICountRouteStrategy{
    
    @Override
    public String execute(ContextImpl context) {
        List<INode> nodes = context.getNodeList();
        if (nodes.size() != 2)return null;
        INode startNode = nodes.get(0);
        INode endNode = nodes.get(1);
        Integer stopNum = (Integer)context.getAttribute("maxStopNum");
        int routesNum = 0;
        routesNum = findRoutesNumWithMaxStop(startNode,endNode,stopNum);
        return String.valueOf(routesNum);
    }
    
    private int findRoutesNumWithMaxStop(INode startNode,INode endNode, int limitStop){
        int routeNum = 0;
        if (limitStop > 0){
            for (INode node : startNode.getThroughNodes()){
                //could be in every level 
                if (node.equals(endNode)){
                    routeNum++;
                } 
                if (limitStop > 1){
                  //recursively find next level's node for limitStop times
                    routeNum += findRoutesNumWithMaxStop(node, endNode,limitStop - 1);
                }
            }
        }
        return routeNum;
    }

}
