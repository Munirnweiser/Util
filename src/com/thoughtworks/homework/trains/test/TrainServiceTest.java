package com.thoughtworks.homework.trains.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.thoughtworks.homework.trains.service.ITrainService;
import com.thoughtworks.homework.trains.service.TrainServiceImpl;

public class TrainServiceTest extends TestCase{
    ITrainService trainService;
    
    @Override
    protected void setUp() throws Exception {
        trainService = new TrainServiceImpl();
    }
    @Test
    public void testServiceThroughRoute(){
        trainService.serviceThroughRoute("A", "B", 5);
        assertEquals(5, trainService.getDistanceOfRoute("A","B"));
    }
    
}
