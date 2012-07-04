package com.thoughtworks.homework.trains.service;

public interface ITrainService {
    void serviceThroughRoute(String startNodeName, String toNodeName, int distance);
    int getDistanceOfRoute(String...names);
    int getNumberOfRouteWithMaxStop(String startNodeName, String endNodeName, int maxStop);
    int getNumberOfRouteWithExactlyStop(String startNodeName, String endNodeName, int exactlyStop);
    int getShortestDistance(String startNodeName, String endNodeName);
    int getNumberOfRouteWithLimitedDistance(String startNodeName, String endNodeName,int limitDistance);
}
