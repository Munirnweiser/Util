package com.thoughtworks.homework.trains.test;

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

    @Override
    protected void setUp() throws Exception {
        A = NodeFactory.createNode("A");
        B = NodeFactory.createNode("B");
        C = NodeFactory.createNode("C");
        D = NodeFactory.createNode("D");
        E = NodeFactory.createNode("E");
        Trains.setRoute(A, B, 5);
        Trains.setRoute(B, C, 4);
        Trains.setRoute(C, D, 8);
        Trains.setRoute(D, C, 8);
        Trains.setRoute(D, E, 6);
        Trains.setRoute(A, D, 5);
        Trains.setRoute(C, E, 2);
        Trains.setRoute(E, B, 3);
        Trains.setRoute(A, E, 7);
    }

    @Override
    protected void tearDown() throws Exception {
        A = null;
        B = null;
        C = null;
        D = null;
        E = null;
    }

    // 1. The distance of the route A-B-C.
    public void testResult1() {
        distance = Trains.getRouteDistance(A, B, C);
        assertEquals("9", distance);
    }
    
    // 2. The distance of the route A-D.
    public void testResult2() {
        distance = Trains.getRouteDistance(A, D);
        assertEquals("5", distance);
    }
    
    // 3. The distance of the route A-D-C.
    public void testResult3() {
        distance = Trains.getRouteDistance(A, D, C);
        assertEquals("13", distance);
    }
    
    // 4. The distance of the route A-E-B-C-D.
    public void testResult4() {
        distance = Trains.getRouteDistance(A, E, B, C, D);
        assertEquals("22", distance);
    }
    
    // 5. The distance of the route A-E-D.
    public void testResult5() {
        distance = Trains.getRouteDistance(A, E, D);
        assertEquals("NO SUCH ROUTE", distance);
    }
    
    // 6. The number of trips starting at C and ending at C with a maximum of 3 stops.  
    //In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
    public void testResult6() {
        distance = Trains.getRouteDistance(A, D);
        assertEquals("2", distance);
    }
    
    // 7. The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, 
    //there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
    public void testResult7() {
        distance = Trains.getRouteDistance(A, D);
        assertEquals("3", distance);
    }
    
    // 8. The length of the shortest route (in terms of distance to travel) from A to C.
    public void testResult8() {
        distance = Trains.getRouteDistance(A, D);
        assertEquals("9", distance);
    }
    
    // 9. The length of the shortest route (in terms of distance to travel) from B to B.
    public void testResult9() {
        distance = Trains.getRouteDistance(A, D);
        assertEquals("9", distance);
    }
    
    // 10. The number of different routes from C to C with a distance of less than 30.  
    //In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC. 
    public void testQ10() {
        distance = Trains.getRouteDistance(A, D);
        assertEquals("7", distance);
    }
    
}
