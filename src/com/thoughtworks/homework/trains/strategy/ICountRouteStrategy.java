package com.thoughtworks.homework.trains.strategy;

import java.util.List;

import com.thoughtworks.homework.trains.model.IRoute;


public interface ICountRouteStrategy {
    public List<IRoute> countRoutes(IContext context);
}
