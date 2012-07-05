package com.thoughtworks.homework.trains.strategy;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.homework.trains.Constants;
import com.thoughtworks.homework.trains.TrainsFactory;
import com.thoughtworks.homework.trains.model.INode;
import com.thoughtworks.homework.trains.model.IRoute;

public class CountRoutesNumWithLimitedDistanceStrategy implements ICountRouteStrategy {
    private ICountRouteStrategy coutRouteStrategy = new CountNoCircleRoutesBetweenTwoNodeStrategy();
    private List<IRoute> routeList;

    @Override
    public List<IRoute> countRoutes(IContext context) {
        List<String> nodes = context.getNodeNames();
        if (nodes.size() != 2)
            return null;
        INode endNode = TrainsFactory.getNode(nodes.get(1));
        int limitedDistance = (Integer) context.getAttribute(Constants.LIMITED_DISTANCE);
        List<IRoute> noCircleRoutes = coutRouteStrategy.countRoutes(context);
        //one circle
        IContext tempContext = TrainsFactory.getContext(endNode.getName(),endNode.getName());
        List<IRoute> circleRouteList = coutRouteStrategy.countRoutes(tempContext);
        routeList = new ArrayList<IRoute>();
        getRoutesWithLimitedDistance(noCircleRoutes, circleRouteList, limitedDistance);
        return routeList;
    }
    
    private void getRoutesWithLimitedDistance(List<IRoute> noCircleList, List<IRoute> circleRouteList, int limit){
        for (IRoute route : noCircleList){
            if (route.getDistance() < limit){
                routeList.add(route);
                for (IRoute r : circleRouteList){
                    //could concat num circle
                    int num = (limit - route.getDistance()) / r.getDistance();
                    // less than limit, prevent equals
                    if (num * r.getDistance() < (limit - route.getDistance())){
                        IRoute tempRoute = route;
                        for (int i = 0 ; i < num; i++){
                            tempRoute = TrainsFactory.getRoute(tempRoute.getNodeNames());
                            for (int j = 1; j < r.getNodeNames().length; j++){
                                tempRoute.addNode(r.getNodeNames()[j]);
                            }
                            routeList.add(tempRoute);
                        }
                    }
                }
            }
        }
    }

}
