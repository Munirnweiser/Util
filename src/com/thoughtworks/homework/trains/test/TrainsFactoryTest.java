package com.thoughtworks.homework.trains.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.thoughtworks.homework.trains.TrainsFactory;
import com.thoughtworks.homework.trains.model.INode;
import com.thoughtworks.homework.trains.model.IRoute;
import com.thoughtworks.homework.trains.service.ITrainService;
import com.thoughtworks.homework.trains.service.TrainServiceImpl;

public class TrainsFactoryTest extends TestCase{
    
    @Override
    protected void setUp() throws Exception {
        ITrainService service = new TrainServiceImpl();
        service.serviceThroughRoute("A", "B", 5);
    }
    
    @Test
    public void testGetNode(){
        INode node1 = TrainsFactory.getNode("A"); 
        INode node2 = TrainsFactory.getNode("A"); 
        assertSame(node1, node2);
    }
    
    @Test
    public void testGetRoute(){
        IRoute route1 = TrainsFactory.getRoute("A","B");
        IRoute route2 = TrainsFactory.getRoute("A","B");
        assertNotSame(route1, route2);
    }
}
