package com.thoughtworks.homework.trains.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import junit.framework.TestCase;

import com.thoughtworks.homework.trains.Trains;
import com.thoughtworks.homework.trains.model.Node;
import com.thoughtworks.homework.trains.model.NodeFactory;

public class TrainsTest extends TestCase {
    private Node A;
    private Node B;
    private Node C;
    private Node D;
    private Node E;
    private String distance;

    @Before
    protected void setUp() throws Exception {
        A = NodeFactory.createNode("A");
        B = NodeFactory.createNode("B");
        C = NodeFactory.createNode("C");
        D = NodeFactory.createNode("D");
        E = NodeFactory.createNode("E");
        Trains.serviceRoute(A, B, 5);
        Trains.serviceRoute(B, C, 4);
        Trains.serviceRoute(C, D, 8);
        Trains.serviceRoute(D, C, 8);
        Trains.serviceRoute(D, E, 6);
        Trains.serviceRoute(A, D, 5);
        Trains.serviceRoute(C, E, 2);
        Trains.serviceRoute(E, B, 3);
        Trains.serviceRoute(A, E, 7);
    }

    @After
    protected void tearDown() throws Exception {
        A = null;
        B = null;
        C = null;
        D = null;
        E = null;
    }

    // 1. The distance of the route A-B-C.
    @Test
    public void testOutPut1() {
        distance = Trains.getRouteDistance(A, B, C);
        assertEquals("9", distance);
    }
    
    // 2. The distance of the route A-D.
    @Test
    public void testOutPut2() {
        distance = Trains.getRouteDistance(A, D);
        assertEquals("5", distance);
    }
    
    // 3. The distance of the route A-D-C.
    @Test
    public void testOutPut3() {
        distance = Trains.getRouteDistance(A, D, C);
        assertEquals("13", distance);
    }
    
    // 4. The distance of the route A-E-B-C-D.
    @Test
    public void testOutPut4() {
        distance = Trains.getRouteDistance(A, E, B, C, D);
        assertEquals("22", distance);
    }
    
    // 5. The distance of the route A-E-D.
    @Test
    public void testOutPut5() {
        distance = Trains.getRouteDistance(A, E, D);
        assertEquals("NO SUCH ROUTE", distance);
    }
    
    // 6. The number of trips starting at C and ending at C with a maximum of 3 stops.  
    //In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
    @Test
    public void testOutPut6() {
        distance = Trains.getRouteDistance(A, D);
        assertEquals("2", distance);
    }
    
    // 7. The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, 
    //there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
    @Test
    public void testOutPut7() {
        distance = Trains.getRouteDistance(A, D);
        assertEquals("3", distance);
    }
    
    // 8. The length of the shortest route (in terms of distance to travel) from A to C.
    @Test
    public void testOutPut8() {
        distance = Trains.getRouteDistance(A, D);
        assertEquals("9", distance);
    }
    
    // 9. The length of the shortest route (in terms of distance to travel) from B to B.
    @Test
    public void testOutPut9() {
        distance = Trains.getRouteDistance(A, D);
        assertEquals("9", distance);
    }
    
    // 10. The number of different routes from C to C with a distance of less than 30.  
    //In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC. 
    @Test
    public void testOutPut10() {
        distance = Trains.getRouteDistance(A, D);
        assertEquals("7", distance);
    }
    
}
