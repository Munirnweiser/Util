package com.thoughtworks.homework.trains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.thoughtworks.homework.trains.model.IRoute;
import com.thoughtworks.homework.trains.service.ITrainService;
import com.thoughtworks.homework.trains.service.TrainServiceImpl;
import com.thoughtworks.homework.trains.strategy.CountRoutesNumWithExactlyStopStrategy;
import com.thoughtworks.homework.trains.strategy.CountRoutesNumWithLimitedDistanceStrategy;
import com.thoughtworks.homework.trains.strategy.CountRoutesNumWithMaxStopStrategy;
import com.thoughtworks.homework.trains.strategy.CountShortestDistanceStrategy;
import com.thoughtworks.homework.trains.strategy.IContext;

public class TrainsMain {
    private static ITrainService trainService = new TrainServiceImpl();

    public static void setTrainService(ITrainService newTrainService) {
        trainService = newTrainService;
    }

    public static void print(Object obj) {
        System.out.println(obj);
    }

    public static void initTrainServiceData(String graph){
        String[] nodeData = graph.split(",");
        for (String s : nodeData){
            s = s.trim();
            String fromNodeName = String.valueOf(s.charAt(0));
            String toNodeName = String.valueOf(s.charAt(1));
            Integer distance = Integer.valueOf(String.valueOf(s.charAt(2)));
            trainService.serviceThroughRoute(fromNodeName, toNodeName, distance);
        }
    }
    
    public static String getDistanceOfRoute(String... names) {
        int distance = trainService.getDistanceOfRoute(names);
        if (distance > 0) {
            return String.valueOf(distance);
        } else {
            return Constants.NO_SUCH_ROUTE;
        }
    }

    public static int getNumberOfRouteWithMaxStop(String startNodeName, String endNodeName, int maxStop) {
        IContext context = TrainsFactory.getContext(startNodeName, endNodeName);
        context.setAttribute(Constants.MAX_STOP, maxStop);
        context.setCountRouteStrategy(new CountRoutesNumWithMaxStopStrategy());
        List<IRoute> routes = trainService.countRoutes(context);
        if (routes != null && !routes.isEmpty()) {
            return routes.size();
        }
        return 0;
    }

    public static int getNumberOfRouteWithExactlyStop(String startNodeName, String endNodeName, int exactlyStop) {
        IContext context = TrainsFactory.getContext(startNodeName, endNodeName);
        context.setAttribute(Constants.EXACTLY_STOP, exactlyStop);
        context.setCountRouteStrategy(new CountRoutesNumWithExactlyStopStrategy());
        List<IRoute> routes = trainService.countRoutes(context);
        if (routes != null && !routes.isEmpty()) {
            return routes.size();
        }
        return 0;
    }

    public static int getShortestDistance(String startNodeName, String endNodeName) {
        IContext context = TrainsFactory.getContext(startNodeName, endNodeName);
        context.setCountRouteStrategy(new CountShortestDistanceStrategy());
        List<IRoute> routes = trainService.countRoutes(context);
        if (routes != null && routes.size() == 1) {
            return routes.get(0).getDistance();
        }
        return 0;
    }

    public static int getNumberOfRouteWithLimitedDistance(String startNodeName, String endNodeName, int limitDistance) {
        IContext context = TrainsFactory.getContext(startNodeName, endNodeName);
        context.setAttribute(Constants.LIMITED_DISTANCE, limitDistance);
        context.setCountRouteStrategy(new CountRoutesNumWithLimitedDistanceStrategy());
        List<IRoute> routes = trainService.countRoutes(context);
        if (routes != null && !routes.isEmpty()) {
            return routes.size();
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
            print("Please Input the graph(for example:AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7):");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String graph = br.readLine();
            try {
                initTrainServiceData(graph);
            } catch (Exception e) {
                print("Error input!! Please rerun the program.");
            }
            int i = 0;
            print("Output #" + (++i) +":" + getDistanceOfRoute("A", "B", "C"));
            print("Output #" + (++i) +":" + getDistanceOfRoute("A", "D"));
            print("Output #" + (++i) +":" + getDistanceOfRoute("A", "D", "C"));
            print("Output #" + (++i) +":" + getDistanceOfRoute("A", "E", "B", "C", "D"));
            print("Output #" + (++i) +":" + getDistanceOfRoute("A", "E", "D"));
            print("Output #" + (++i) +":" + getNumberOfRouteWithMaxStop("C", "C", 3));
            print("Output #" + (++i) +":" + getNumberOfRouteWithExactlyStop("A", "C", 4));
            print("Output #" + (++i) +":" + getShortestDistance("A", "C"));
            print("Output #" + (++i) +":" + getShortestDistance("B", "B"));
            print("Output #" + (++i) +":" + getNumberOfRouteWithLimitedDistance("C", "C", 30));
    }
}
