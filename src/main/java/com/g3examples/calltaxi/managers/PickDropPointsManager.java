package com.g3examples.calltaxi.managers;

import java.util.HashMap;
import java.util.Map;

public class PickDropPointsManager {
    private static Map<String, Integer> locationToDistanceMap = new HashMap<>();

    public void addPickDropPoint(String location, Integer distanceFromStartingLocation) {
        locationToDistanceMap.put(location, distanceFromStartingLocation);
    }

    public int distanceBetweenTwoPoints(String point1, String point2) {
        return Math.abs(locationToDistanceMap.get(point1) - locationToDistanceMap.get(point2));
    }
}
