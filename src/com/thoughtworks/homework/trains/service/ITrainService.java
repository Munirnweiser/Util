package com.thoughtworks.homework.trains.service;

import java.util.List;

import com.thoughtworks.homework.trains.model.IRoute;
import com.thoughtworks.homework.trains.strategy.IContext;

public interface ITrainService {
    void serviceThroughRoute(String startNodeName, String toNodeName, int distance);
    int getDistanceOfRoute(String...names);
    List<IRoute> countRoutes(IContext context);
}
