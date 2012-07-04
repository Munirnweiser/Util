package com.thoughtworks.homework.trains;

import com.thoughtworks.homework.trains.model.INode;
import com.thoughtworks.homework.trains.strategy.Context;
import com.thoughtworks.homework.trains.strategy.IRouteStrategy;

public class TrainService {
    private IRouteStrategy routeStrategy;
    public TrainService(IRouteStrategy routeStrategy){
        this.routeStrategy = routeStrategy;
    }
    public void setRouteStrategy(IRouteStrategy routeStrategy){
        this.routeStrategy = routeStrategy;
    }
    public void serviceRoute(INode n1, INode n2, int distance) {
        n1.setRoute(n2, distance);
    }

    public String execute(Context context) {
        return routeStrategy.execute(context);
    }

}
