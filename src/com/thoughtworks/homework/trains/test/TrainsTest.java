package com.thoughtworks.homework.trains.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.homework.trains.TrainService;
import com.thoughtworks.homework.trains.model.INode;
import com.thoughtworks.homework.trains.model.NodeFactory;
import com.thoughtworks.homework.trains.strategy.Context;
import com.thoughtworks.homework.trains.strategy.CountDistanceStrategy;
import com.thoughtworks.homework.trains.strategy.CountRoutesNumWithExactlyStopStrategy;
import com.thoughtworks.homework.trains.strategy.CountRoutesNumWithMaxStopStrategy;
import com.thoughtworks.homework.trains.strategy.CountShortestDistanceStrategy;

public class TrainsTest extends TestCase {
    private TrainService trainService;
    private INode A;
    private INode B;
    private INode C;
    private INode D;
    private INode E;
    private String result;
    private Context context;

    @Before
    protected void setUp() throws Exception {
        A = NodeFactory.createNode("A");
        B = NodeFactory.createNode("B");
        C = NodeFactory.createNode("C");
        D = NodeFactory.createNode("D");
        E = NodeFactory.createNode("E");
        trainService = new TrainService(new CountDistanceStrategy());
        trainService.serviceRoute(A, B, 5);
        trainService.serviceRoute(B, C, 4);
        trainService.serviceRoute(C, D, 8);
        trainService.serviceRoute(D, C, 8);
        trainService.serviceRoute(D, E, 6);
        trainService.serviceRoute(A, D, 5);
        trainService.serviceRoute(C, E, 2);
        trainService.serviceRoute(E, B, 3);
        trainService.serviceRoute(A, E, 7);
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
        context = new Context(A, B, C);
        result = trainService.execute(context);
        assertEquals("9", result);
    }
    
    // 2. The distance of the route A-D.
    @Test
    public void testOutPut2() {
        context = new Context(A, D);
        result = trainService.execute(context);
        assertEquals("5", result);
    }
    
    // 3. The distance of the route A-D-C.
    @Test
    public void testOutPut3() {
        context = new Context(A, D, C);
        result = trainService.execute(context);
        assertEquals("13", result);
    }
    
    // 4. The distance of the route A-E-B-C-D.
    @Test
    public void testOutPut4() {
        context = new Context(A, E, B, C, D);
        result = trainService.execute(context);
        assertEquals("22", result);
    }
    
    // 5. The distance of the route A-E-D.
    @Test
    public void testOutPut5() {
        context = new Context(A, E, D);
        result = trainService.execute(context);
        assertEquals("NO SUCH ROUTE", result);
    }
    
    // 6. The number of trips starting at C and ending at C with a maximum of 3 stops.  
    //In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
    @Test
    public void testOutPut6() {
        context = new Context(C, C);
        context.setAttribute("maxStopNum", 3);
        trainService.setRouteStrategy(new CountRoutesNumWithMaxStopStrategy());
        result = trainService.execute(context);
        assertEquals("2", result);
    }
    
    // 7. The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, 
    //there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
    @Test
    public void testOutPut7() {
        context = new Context(A, C);
        context.setAttribute("exactlyStopNum", 4);
        trainService.setRouteStrategy(new CountRoutesNumWithExactlyStopStrategy());
        result = trainService.execute(context);
        assertEquals("3", result);
    }
    
    // 8. The length of the shortest route (in terms of distance to travel) from A to C.
    @Test
    public void testOutPut8() {
        context = new Context(A, C);
        trainService.setRouteStrategy(new CountShortestDistanceStrategy());
        result = trainService.execute(context);
        assertEquals("9", result);
    }
    
    // 9. The length of the shortest route (in terms of distance to travel) from B to B.
    @Test
    public void testOutPut9() {
        context = new Context(B, B);
        trainService.setRouteStrategy(new CountShortestDistanceStrategy());
        result = trainService.execute(context);
        assertEquals("9", result);
    }
    
    // 10. The number of different routes from C to C with a distance of less than 30.  
    //In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC. 
    @Test
    public void testOutPut10() {
        context = new Context(B, B);
        context.setAttribute("exactlyStopNum", 4);
        result = trainService.execute(context);
        assertEquals("7", result);
    }
    
}
