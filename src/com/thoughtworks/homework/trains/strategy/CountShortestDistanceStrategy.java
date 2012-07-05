package com.thoughtworks.homework.trains.strategy;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.homework.trains.model.IRoute;

public class CountShortestDistanceStrategy implements ICountRouteStrategy {
    private ICountRouteStrategy coutRouteStrategy = new CountNoCircleRoutesBetweenTwoNodeStrategy();

    @Override
    public List<IRoute> countRoutes(IContext context) {
        List<IRoute> returnRoutes = new ArrayList<IRoute>();
        List<IRoute> noCircleRoutes = coutRouteStrategy.countRoutes(context);
        IRoute shortestRoute = null;
        for (IRoute route : noCircleRoutes) {
            if (shortestRoute == null || route.getDistance() < shortestRoute.getDistance()){
                shortestRoute = route;
            } 
        }
        returnRoutes.add(shortestRoute);
        return returnRoutes;
    }
}
