package com.thoughtworks.homework.trains.model;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.homework.trains.exception.UnSupportRouteException;

public class RouteImpl implements IRoute{
    private List<INode> nodeList = new ArrayList<INode>();

    public RouteImpl(INode ... nodes) throws UnSupportRouteException{
        for (int i = 0; i < nodes.length - 1; i++){
            if (nodes[i].hasThroughRouteTo(nodes[i+1])){
                nodeList.add(nodes[i]);
            } else {
                throw new UnSupportRouteException();
            }
            nodeList.add(nodes[nodes.length - 1]);
        }
    }
    
    @Override
    public String getPath() {
        StringBuilder sb = new StringBuilder();
        for (INode node : nodeList){
            sb.append(node.getName() + "-");
        }
        if (nodeList.size() > 0){
            return sb.substring(0, sb.length()-1);
        } else {
            return "";
        } 
    }

    @Override
    public int getStops() {
        return nodeList.size() - 1;
    }

    @Override
    public int getDistance() {
        int distance = 0;
        for (int i = 0; i < nodeList.size() - 1; i++){
            distance += nodeList.get(i).getDistance(nodeList.get(i+1));
        }
        return distance;
    }
}
