package com.thoughtworks.homework.trains.model;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.homework.trains.exception.UnSupportRouteException;

public class RouteImpl implements IRoute{
    private List<INode> nodeList = new ArrayList<INode>();

    RouteImpl(INode ... nodes) throws UnSupportRouteException{
        for (INode node : nodes){
            if (!addNode(node)){
                throw new UnSupportRouteException();
            }
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
    
    @Override
    public boolean addNode(INode node) {
        if (nodeList.size() == 0){
            nodeList.add(node);
        } else {
            INode lastNode = nodeList.get(nodeList.size() - 1);
            if (lastNode.hasThroughRouteTo(node)){
                nodeList.add(node);
            } else {
                return false;
            }
        }
        return true;
    }
}
