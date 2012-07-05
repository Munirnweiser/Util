package com.thoughtworks.homework.trains.test;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.thoughtworks.homework.trains.Constants;
import com.thoughtworks.homework.trains.TrainsFactory;
import com.thoughtworks.homework.trains.model.IRoute;
import com.thoughtworks.homework.trains.service.ITrainService;
import com.thoughtworks.homework.trains.service.TrainServiceImpl;
import com.thoughtworks.homework.trains.strategy.CountNoCircleRoutesBetweenTwoNodeStrategy;
import com.thoughtworks.homework.trains.strategy.CountRoutesNumWithExactlyStopStrategy;
import com.thoughtworks.homework.trains.strategy.CountRoutesNumWithLimitedDistanceStrategy;
import com.thoughtworks.homework.trains.strategy.CountRoutesNumWithMaxStopStrategy;
import com.thoughtworks.homework.trains.strategy.CountShortestDistanceStrategy;
import com.thoughtworks.homework.trains.strategy.IContext;

public class StrategyTest extends TestCase{
    ITrainService service;
    IContext context;
    
    @Override
    protected void setUp() throws Exception {
        service = new TrainServiceImpl();
        service.serviceThroughRoute("A", "B", 5);
        service.serviceThroughRoute("B", "C", 3);
        service.serviceThroughRoute("C", "B", 4);
        context = TrainsFactory.getContext("A","C");
    }
    
    @Test
    public void testCountNoCircleRoutesBetweenTwoNodeStrategy(){
        context.setCountRouteStrategy(new CountNoCircleRoutesBetweenTwoNodeStrategy());
        List<IRoute> list = service.countRoutes(context);
        assertEquals(1, list.size());
        assertEquals(5 + 3, list.get(0).getDistance());
    }
    
    @Test
    public void testCountRoutesNumWithExactlyStopStrategy(){
        context.setCountRouteStrategy(new CountRoutesNumWithExactlyStopStrategy());
        context.setAttribute(Constants.EXACTLY_STOP, 4);
        List<IRoute> list = service.countRoutes(context);
        assertEquals(1, list.size());
        assertEquals(4, list.get(0).getStops());
        assertEquals("[A, B, C, B, C]", Arrays.toString(list.get(0).getNodeNames()));
    }
    
    @Test
    public void testCountRoutesNumWithMaxStopStrategy(){
        context.setCountRouteStrategy(new CountRoutesNumWithMaxStopStrategy());
        context.setAttribute(Constants.MAX_STOP, 6);
        List<IRoute> list = service.countRoutes(context);
        assertEquals(3, list.size());
        assertEquals("[A, B, C]", Arrays.toString(list.get(0).getNodeNames()));
        assertEquals("[A, B, C, B, C]", Arrays.toString(list.get(1).getNodeNames()));
        assertEquals("[A, B, C, B, C, B, C]", Arrays.toString(list.get(2).getNodeNames()));
    }
    
    @Test
    public void testCountShortestDistanceStrategy(){
        context.setCountRouteStrategy(new CountShortestDistanceStrategy());
        List<IRoute> list = service.countRoutes(context);
        assertEquals(1, list.size());
        //A-B-C
        assertEquals(8, list.get(0).getDistance());
    }
    
    @Test
    public void testCountRoutesNumWithLimitedDistanceStrategy(){
        context.setCountRouteStrategy(new CountRoutesNumWithLimitedDistanceStrategy());
        context.setAttribute(Constants.LIMITED_DISTANCE, 23);
        List<IRoute> list = service.countRoutes(context);
        assertEquals(3, list.size());
        //A-B-C
        assertEquals(8, list.get(0).getDistance());
        //A-B-C-B-C
        assertEquals(15, list.get(1).getDistance());
        //A-B-C-B-C-B-C
        assertEquals(22, list.get(2).getDistance());
    }
}
