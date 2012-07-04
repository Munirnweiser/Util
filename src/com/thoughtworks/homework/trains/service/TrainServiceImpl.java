package com.thoughtworks.homework.trains.service;

import java.util.List;

import com.thoughtworks.homework.trains.model.INode;
import com.thoughtworks.homework.trains.model.IRoute;
import com.thoughtworks.homework.trains.model.TrainsFactory;
import com.thoughtworks.homework.trains.strategy.IContext;
import com.thoughtworks.homework.trains.strategy.ICountRouteStrategy;

public class TrainServiceImpl implements ITrainService{
    private ICountRouteStrategy routeStrategy;
    
    public void setRouteStrategy(ICountRouteStrategy routeStrategy){
        this.routeStrategy = routeStrategy;
    }
    @Override
    public void serviceThroughRoute(String startNodeName, String toNodeName,
            int distance) {
        INode startNode = TrainsFactory.getNode(startNodeName);
        INode toNode = TrainsFactory.getNode(toNodeName);
        startNode.setRoute(toNode, distance);
    }
    @Override
    public int getDistanceOfRoute(String... names) {
        IRoute route = TrainsFactory.getRoute(names);
        if (route != null){
            return route.getDistance();
        }
        return -1;
    }
    @Override
    public List<IRoute> countRoutes(IContext context) {
        return routeStrategy.countRoutes(context);
    }

}
