package com.thoughtworks.homework.trains.strategy;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.homework.trains.Constants;
import com.thoughtworks.homework.trains.TrainsFactory;
import com.thoughtworks.homework.trains.model.INode;
import com.thoughtworks.homework.trains.model.IRoute;

public class CountRoutesNumWithExactlyStopStrategy implements ICountRouteStrategy {
    private ICountRouteStrategy coutRouteStrategy = new CountNoCircleRoutesBetweenTwoNodeStrategy();
    private List<IRoute> routeList;

    @Override
    public List<IRoute> countRoutes(IContext context) {
        List<String> nodes = context.getNodeNames();
        if (nodes.size() != 2)
            return null;
        INode endNode = TrainsFactory.getNode(nodes.get(1));
        int exactlyStop = (Integer) context.getAttribute(Constants.EXACTLY_STOP);
        List<IRoute> noCircleRoutes = coutRouteStrategy.countRoutes(context);
        //get one circle route, from endNode to endNode
        IContext tempContext = TrainsFactory.getContext(endNode.getName(),endNode.getName());
        List<IRoute> circleRouteList = coutRouteStrategy.countRoutes(tempContext);
        routeList = new ArrayList<IRoute>();
        getRoutesWithExactlyStop(noCircleRoutes, circleRouteList, exactlyStop);
        return routeList;
    }
    
    private void getRoutesWithExactlyStop(List<IRoute> noCircleList, List<IRoute> circleRouteList, int stop){
        for (IRoute route : noCircleList){
            if (route.getStops() == stop){
                routeList.add(route);
            } else if (route.getStops() < stop){
                for (IRoute r : circleRouteList){
                    // concat endNode's circle route to reach the stop 
                    if ((stop - route.getStops()) % r.getStops() == 0){
                        int num = (stop - route.getStops()) / r.getStops();
                        IRoute tempRoute = TrainsFactory.getRoute(route.getNodeNames());
                        for (int i = 0 ; i < num; i++){
                            for (int j = 1; j < r.getNodeNames().length; j++){
                                tempRoute.addNode(r.getNodeNames()[j]);
                            }
                        }
                        routeList.add(tempRoute);
                    }
                }
            }
        }
    }

}
