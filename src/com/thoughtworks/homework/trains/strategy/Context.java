package com.thoughtworks.homework.trains.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.homework.trains.model.INode;

public class Context {
    private List<INode> nodeList = new ArrayList<INode>();
    private Map<String,Object> attributeMap = new HashMap<String, Object>();
    public Context(INode ... nodes){
        for (INode node : nodes){
            nodeList.add(node);
        }
    }
    
    public void setAttribute(String param, Object value){
        attributeMap.put(param, value);
    }
    
    //just for strategies
    Object getAttribute(String param){
        return attributeMap.get(param);
    }
    
    List<INode> getNodeList(){
        return nodeList;
    }
}
