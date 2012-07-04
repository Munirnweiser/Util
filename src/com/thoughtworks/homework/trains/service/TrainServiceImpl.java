package com.thoughtworks.homework.trains.service;

import java.util.List;

import com.thoughtworks.homework.trains.Constants;
import com.thoughtworks.homework.trains.model.INode;
import com.thoughtworks.homework.trains.model.IRoute;
import com.thoughtworks.homework.trains.model.TrainsFactory;
import com.thoughtworks.homework.trains.strategy.Context;
import com.thoughtworks.homework.trains.strategy.IRouteStrategy;

public class TrainServiceImpl implements ITrainService{
    private IRouteStrategy routeStrategy;
    
    public void setRouteStrategy(IRouteStrategy routeStrategy){
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
    public int getNumberOfRouteWithMaxStop(String startNodeName,
            String endNodeName, int maxStop) {
        Context context = new Context(startNodeName, endNodeName);
        context.setAttribute(Constants.MAX_STOP, maxStop);
        List<IRoute> routes = routeStrategy.execute(context);
        if (routes != null && !routes.isEmpty()){
            return routes.size();
        }
        return 0;
    }
    @Override
    public int getNumberOfRouteWithExactlyStop(String startNodeName,
            String endNodeName, int exactlyStop) {
        Context context = new Context(startNodeName, endNodeName);
        context.setAttribute(Constants.EXACTLY_STOP, exactlyStop);
        List<IRoute> routes = routeStrategy.execute(context);
        if (routes != null && !routes.isEmpty()){
            return routes.size();
        }
        return 0;
    }
    @Override
    public int getShortestDistance(String startNodeName, String endNodeName) {
        Context context = new Context(startNodeName, endNodeName);
        List<IRoute> routes = routeStrategy.execute(context);
        if (routes != null && routes.size() == 1){
            return routes.get(0).getDistance();
        }
        return 0;
    }
    @Override
    public int getNumberOfRouteWithLimitedDistance(String startNodeName,
            String endNodeName, int limitDistance) {
        Context context = new Context(startNodeName, endNodeName);
        context.setAttribute(Constants.LIMIT_DISTANCE, limitDistance);
        List<IRoute> routes = routeStrategy.execute(context);
        if (routes != null && !routes.isEmpty()){
            return routes.size();
        }
        return 0;
    }

}
