package com.thoughtworks.homework.trains.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.homework.trains.Constants;
import com.thoughtworks.homework.trains.TrainsMain;

public class TrainsMainTest extends TestCase {

    @Before
    protected void setUp() throws Exception {
        String graph = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        TrainsMain.initTrainServiceData(graph);
    }

    @After
    protected void tearDown() throws Exception {
    }

    // 1. The distance of the route A-B-C.
    @Test
    public void testOutPut1() {
        assertEquals("9", TrainsMain.getDistanceOfRoute("A", "B", "C"));
    }
    
    // 2. The distance of the route A-D.
    @Test
    public void testOutPut2() {
        assertEquals("5", TrainsMain.getDistanceOfRoute("A", "D"));
    }
    
    // 3. The distance of the route A-D-C.
    @Test
    public void testOutPut3() {
        assertEquals("13", TrainsMain.getDistanceOfRoute("A", "D", "C"));
    }
    
    // 4. The distance of the route A-E-B-C-D.
    @Test
    public void testOutPut4() {
        assertEquals("22", TrainsMain.getDistanceOfRoute("A", "E", "B", "C", "D"));
    }
    
    // 5. The distance of the route A-E-D.
    @Test
    public void testOutPut5() {
        assertEquals(Constants.NO_SUCH_ROUTE, TrainsMain.getDistanceOfRoute("A", "E", "D"));
    }
    
    // 6. The number of trips starting at C and ending at C with a maximum of 3 stops.  
    //In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
    @Test
    public void testOutPut6() {
        assertEquals(2, TrainsMain.getNumberOfRouteWithMaxStop("C", "C", 3));
    }
    
    // 7. The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, 
    //there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
    @Test
    public void testOutPut7() {
        assertEquals(3, TrainsMain.getNumberOfRouteWithExactlyStop("A", "C", 4));
    }
    
    // 8. The length of the shortest route (in terms of distance to travel) from A to C.
    @Test
    public void testOutPut8() {
        assertEquals(9, TrainsMain.getShortestDistance("A", "C"));
    }
    
    // 9. The length of the shortest route (in terms of distance to travel) from B to B.
    @Test
    public void testOutPut9() {
        assertEquals(9, TrainsMain.getShortestDistance("B", "B"));
    }
    
    // 10. The number of different routes from C to C with a distance of less than 30.  
    //In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC. 
    @Test
    public void testOutPut10() {
        assertEquals(7, TrainsMain.getNumberOfRouteWithLimitedDistance("C", "C", 30));
    }
    
}
