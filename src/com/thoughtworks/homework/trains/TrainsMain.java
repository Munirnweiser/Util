package com.thoughtworks.homework.trains;

import com.thoughtworks.homework.trains.service.ITrainService;
import com.thoughtworks.homework.trains.service.TrainServiceImpl;

public class TrainsMain {
    private static ITrainService trainService = new TrainServiceImpl();

    public static void setTrainService(ITrainService newTrainService) {
        trainService = newTrainService;
    }

    public static void print(Object obj){
        System.out.println(obj);
    }
    public static String outPutDistanceOfRoute(String... names) {
        int distance = trainService.getDistanceOfRoute(names);
        if (distance > 0) {
            return String.valueOf(distance);
        } else {
            return Constants.NO_SUCH_ROUTE;
        }
    }

    public static void main(String[] args) {
        print("");
    }
}
