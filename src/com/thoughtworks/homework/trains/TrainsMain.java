package com.thoughtworks.homework.trains;

import java.util.List;

import com.thoughtworks.homework.trains.model.IRoute;
import com.thoughtworks.homework.trains.service.ITrainService;
import com.thoughtworks.homework.trains.service.TrainServiceImpl;
import com.thoughtworks.homework.trains.strategy.ContextImpl;
import com.thoughtworks.homework.trains.strategy.IContext;

public class TrainsMain {
    private static ITrainService trainService = new TrainServiceImpl();

    public static void setTrainService(ITrainService newTrainService) {
        trainService = newTrainService;
    }

    public static void print(Object obj){
        System.out.println(obj);
    }
    public static String outPutDistanceOfRoute(String... names) {
        int distance = trainService.getDistanceOfRoute(names);
        if (distance > 0) {
            return String.valueOf(distance);
        } else {
            return Constants.NO_SUCH_ROUTE;
        }
    }
    
    public int getNumberOfRouteWithMaxStop(String startNodeName,
            String endNodeName, int maxStop) {
        IContext context = new ContextImpl(startNodeName, endNodeName);
        context.setAttribute(Constants.MAX_STOP, maxStop);
        context.setCountRouteStrategy();
        List<IRoute> routes = trainService.countRoutes(context);
        if (routes != null && !routes.isEmpty()){
            return routes.size();
        }
        return 0;
    }
    public int getNumberOfRouteWithExactlyStop(String startNodeName,
            String endNodeName, int exactlyStop) {
        IContext context = new ContextImpl(startNodeName, endNodeName);
        context.setAttribute(Constants.EXACTLY_STOP, exactlyStop);
        context.setCountRouteStrategy();
        List<IRoute> routes = trainService.countRoutes(context);
        if (routes != null && !routes.isEmpty()){
            return routes.size();
        }
        return 0;
    }
    public int getShortestDistance(String startNodeName, String endNodeName) {
        IContext context = new ContextImpl(startNodeName, endNodeName);
        context.setCountRouteStrategy();
        List<IRoute> routes = trainService.countRoutes(context);
        if (routes != null && routes.size() == 1){
            return routes.get(0).getDistance();
        }
        return 0;
    }
    public int getNumberOfRouteWithLimitedDistance(String startNodeName,
            String endNodeName, int limitDistance) {
        IContext context = new ContextImpl(startNodeName, endNodeName);
        context.setAttribute(Constants.LIMIT_DISTANCE, limitDistance);
        context.setCountRouteStrategy();
        List<IRoute> routes = trainService.countRoutes(context);
        if (routes != null && !routes.isEmpty()){
            return routes.size();
        }
        return 0;
    }
    public static void main(String[] args) {
        print("");
    }
}
