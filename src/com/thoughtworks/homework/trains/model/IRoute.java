package com.thoughtworks.homework.trains.model;

import java.util.List;

public interface IRoute {
    public List<String> getNodeNames();
    public int getStops();
    public int getDistance();
}
