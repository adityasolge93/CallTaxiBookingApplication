package com.g3examples.calltaxi.dao;

import java.util.HashMap;
import java.util.Map;

public class PickDropPointsDao implements PickDropPointsDaoInterface {
    private static Map<String, Integer> locationToDistanceMap = new HashMap<>();

    @Override
    public void addPickDropPoint(String location, Integer distanceFromStartingLocation) {
        locationToDistanceMap.put(location, distanceFromStartingLocation);
    }

    @Override
    public int distanceBetweenTwoPoints(String point1, String point2) {
        return Math.abs(locationToDistanceMap.get(point1) - locationToDistanceMap.get(point2));
    }
}
