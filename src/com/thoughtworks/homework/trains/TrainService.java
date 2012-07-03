package com.thoughtworks.homework.trains;

import com.thoughtworks.homework.trains.model.Node;
import com.thoughtworks.homework.trains.strategy.RouteStrategy;

public class TrainService {
    private RouteStrategy routeStrategy;
    public void setRouteStrategy(RouteStrategy routeStrategy){
        this.routeStrategy = routeStrategy;
    }
    public void serviceRoute(Node n1, Node n2, int distance) {
        n1.setRoute(n2, distance);
    }

    public String execute(Node... nodes) {
        return routeStrategy.execute(nodes);
    }

    public static void main(String[] args) {
        System.out.println(null instanceof Object);
    }
}
