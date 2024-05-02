package com.g3examples.calltaxi.managers;

import com.g3examples.calltaxi.models.TaxiDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxiManager {
    private static final Map<String, TaxiDto> taxiMap = new HashMap<>();

    private PickDropPointsManager pickDropPointsManager;

    public TaxiManager(PickDropPointsManager pickDropPointsManager) {
        this.pickDropPointsManager = pickDropPointsManager;
    }

    public TaxiDto getTaxiAtPickupPoint(String pickupPoint, int pickupTime) {
        TaxiDto availableTaxi = null;

        List<TaxiDto> taxis = taxiMap.values().stream().toList();
        for (TaxiDto taxiDto : taxis) {
            if (taxiDto.getAvailableAtTime() == -1 || taxiDto.getAvailableAtTime() <= pickupTime) {
                if (availableTaxi == null) {
                    availableTaxi = taxiDto;
                } else {
                    int distanceOfCurrentTaxiFromPickupPoint = pickDropPointsManager.distanceBetweenTwoPoints(pickupPoint, taxiDto.getCurrentLocation());
                    int distanceOfAvailableTaxiFromPickupPoint = pickDropPointsManager.distanceBetweenTwoPoints(pickupPoint, availableTaxi.getCurrentLocation());
                    if (distanceOfCurrentTaxiFromPickupPoint == distanceOfAvailableTaxiFromPickupPoint) {
                        if (taxiDto.getEarnings() < availableTaxi.getEarnings()) {
                            availableTaxi = taxiDto;
                        }
                    } else if (distanceOfCurrentTaxiFromPickupPoint < distanceOfAvailableTaxiFromPickupPoint) {
                        availableTaxi = taxiDto;
                    }
                }
            }
        }

        return availableTaxi;
    }

    public void updateTaxi(TaxiDto taxiDto) {
        taxiMap.put(taxiDto.getTaxiNo(), taxiDto);
    }

    public void addTaxi(TaxiDto taxiDto) {
        taxiMap.put(taxiDto.getTaxiNo(), taxiDto);
    }

    public List<TaxiDto> getAllTaxis() {
        return taxiMap.values().stream().toList();
    }
}
