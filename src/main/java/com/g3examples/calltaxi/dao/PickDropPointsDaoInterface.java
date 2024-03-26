package com.g3examples.calltaxi.dao;

public interface PickDropPointsDaoInterface {
    void addPickDropPoint(String location, Integer distanceFromStartingLocation);
    int distanceBetweenTwoPoints(String point1, String point2);
}
