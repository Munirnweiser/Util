package com.thoughtworks.homework.trains.strategy;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.homework.trains.Constants;
import com.thoughtworks.homework.trains.model.IRoute;

public class CountRoutesNumWithMaxStopStrategy implements ICountRouteStrategy {
    private ICountRouteStrategy coutRouteStrategy = new CountRoutesNumWithExactlyStopStrategy();

    @Override
    public List<IRoute> countRoutes(IContext context) {
        int maxStop = (Integer) context.getAttribute(Constants.MAX_STOP);
        List<IRoute> returnRoutes = new ArrayList<IRoute>();
        for (int i = 1; i <= maxStop; i++){
            context.setAttribute(Constants.EXACTLY_STOP, i);
            returnRoutes.addAll(coutRouteStrategy.countRoutes(context));
            
        }
        return returnRoutes;
    }

}
