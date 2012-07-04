package com.thoughtworks.homework.trains.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.homework.trains.model.INode;
import com.thoughtworks.homework.trains.model.TrainsFactory;

public class Context {
    private List<INode> nodeList = new ArrayList<INode>();
    private Map<String,Object> attributeMap = new HashMap<String, Object>();
    public Context(String ... names){
        for (String name : names){
            nodeList.add(TrainsFactory.getNode(name));
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
