package com.thoughtworks.homework.trains.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.homework.trains.service.TrainServiceImpl;

public class TrainsTest extends TestCase {
    private TrainServiceImpl trainService;
    private int result;

    @Before
    protected void setUp() throws Exception {
        trainService = new TrainServiceImpl();
        trainService.serviceThroughRoute("A", "B", 5);
        trainService.serviceThroughRoute("B", "C", 4);
        trainService.serviceThroughRoute("C", "D", 8);
        trainService.serviceThroughRoute("D", "C", 8);
        trainService.serviceThroughRoute("D", "E", 6);
        trainService.serviceThroughRoute("A", "D", 5);
        trainService.serviceThroughRoute("C", "E", 2);
        trainService.serviceThroughRoute("E", "B", 3);
        trainService.serviceThroughRoute("A", "E", 7);
    }

    @After
    protected void tearDown() throws Exception {
    }

    // 1. The distance of the route A-B-C.
    @Test
    public void testOutPut1() {
        result = trainService.getDistanceOfRoute("A", "B", "C");
        assertEquals(9, result);
    }
    
    // 2. The distance of the route A-D.
    @Test
    public void testOutPut2() {
        result = trainService.getDistanceOfRoute("A", "D");
        assertEquals(5, result);
    }
    
    // 3. The distance of the route A-D-C.
    @Test
    public void testOutPut3() {
        result = trainService.getDistanceOfRoute("A", "D", "C");
        assertEquals(13, result);
    }
    
    // 4. The distance of the route A-E-B-C-D.
    @Test
    public void testOutPut4() {
        result = trainService.getDistanceOfRoute("A", "E", "B", "C", "D");
        assertEquals(22, result);
    }
    
    // 5. The distance of the route A-E-D.
    @Test
    public void testOutPut5() {
        result = trainService.getDistanceOfRoute("A", "E", "D");
        assertEquals(-1, result);
    }
    
    // 6. The number of trips starting at C and ending at C with a maximum of 3 stops.  
    //In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
    @Test
    public void testOutPut6() {
        result = trainService.getNumberOfRouteWithMaxStop("C", "C", 3);
        assertEquals(2, result);
    }
    
    // 7. The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, 
    //there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
    @Test
    public void testOutPut7() {
        result = trainService.getNumberOfRouteWithExactlyStop("A", "C", 4);
        assertEquals(3, result);
    }
    
    // 8. The length of the shortest route (in terms of distance to travel) from A to C.
    @Test
    public void testOutPut8() {
        result = trainService.getShortestDistance("A", "C");
        assertEquals(9, result);
    }
    
    // 9. The length of the shortest route (in terms of distance to travel) from B to B.
    @Test
    public void testOutPut9() {
        result = trainService.getShortestDistance("B", "B");
        assertEquals(9, result);
    }
    
    // 10. The number of different routes from C to C with a distance of less than 30.  
    //In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC. 
    @Test
    public void testOutPut10() {
        result = trainService.getNumberOfRouteWithLimitedDistance("B", "B", 30);
        assertEquals(7, result);
    }
    
}
