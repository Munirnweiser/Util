package com.thoughtworks.homework.trains.strategy;

import com.thoughtworks.homework.trains.model.Node;

public interface RouteStrategy {
    public String execute(Node ... nodes);
}
