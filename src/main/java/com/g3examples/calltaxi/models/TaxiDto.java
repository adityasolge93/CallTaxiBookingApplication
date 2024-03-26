package com.g3examples.calltaxi.models;

public class TaxiDto {
    private String taxiNo;
    private double earnings;
    private String currentLocation;
    private int availableAtTime;

    public TaxiDto(String taxiNo, double earnings, String currentLocation, int availableAtTime) {
        this.taxiNo = taxiNo;
        this.earnings = earnings;
        this.currentLocation = currentLocation;
        this.availableAtTime = availableAtTime;
    }

    public String getTaxiNo() {
        return taxiNo;
    }

    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getAvailableAtTime() {
        return availableAtTime;
    }

    public void setAvailableAtTime(int availableAtTime) {
        this.availableAtTime = availableAtTime;
    }
}
