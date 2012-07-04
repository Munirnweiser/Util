package com.thoughtworks.homework.trains.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.homework.trains.model.INode;
import com.thoughtworks.homework.trains.model.TrainsFactory;

public class ContextImpl implements IContext{
    private ICountRouteStrategy strategy = new GetRoutesBetweenTwoNodeStrategy();
    private List<INode> nodeList = new ArrayList<INode>();
    private Map<String,Object> attributeMap = new HashMap<String, Object>();
    public ContextImpl(String ... names){
        for (String name : names){
            nodeList.add(TrainsFactory.getNode(name));
        }
    }
    
    public void setAttribute(String param, Object value){
        attributeMap.put(param, value);
    }
    
    public Object getAttribute(String param){
        return attributeMap.get(param);
    }
    
    public List<String> getNodeNames(){
        List<String> list = new ArrayList<String>();
        for (INode node : nodeList){
            list.add(node.getName());
        }
        return list;
    }

    @Override
    public void setCountRouteStrategy(ICountRouteStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public ICountRouteStrategy getCountRouteStrategy() {
        return strategy;
    }
}
