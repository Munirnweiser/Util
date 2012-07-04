package com.thoughtworks.homework.trains.strategy;

import java.util.List;

import com.thoughtworks.homework.trains.model.IRoute;


public interface IRouteStrategy {
    public List<IRoute> execute(Context context);
}
