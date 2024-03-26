package com.g3examples.calltaxi.managers;

import com.g3examples.calltaxi.dao.PickDropPointsDaoInterface;
import com.g3examples.calltaxi.dao.TaxiDaoInterface;
import com.g3examples.calltaxi.models.TaxiDto;

import java.util.List;

public class TaxiManager {

    private TaxiDaoInterface taxiDao;
    private PickDropPointsDaoInterface pickDropPointsDao;

    public TaxiManager(TaxiDaoInterface taxiDao, PickDropPointsDaoInterface pickDropPointsDao) {
        this.taxiDao = taxiDao;
        this.pickDropPointsDao = pickDropPointsDao;
    }

    public TaxiDto getTaxiAtPickupPoint(String pickupPoint, int pickupTime) {
        TaxiDto availableTaxi = null;

        List<TaxiDto> taxis = taxiDao.getAllTaxis();
        for (TaxiDto taxiDto : taxis) {
            if (taxiDto.getAvailableAtTime() == -1 || taxiDto.getAvailableAtTime() <= pickupTime) {
                if (availableTaxi == null) {
                    availableTaxi = taxiDto;
                } else {
                    int distanceOfCurrentTaxiFromPickupPoint = pickDropPointsDao.distanceBetweenTwoPoints(pickupPoint, taxiDto.getCurrentLocation());
                    int distanceOfAvailableTaxiFromPickupPoint = pickDropPointsDao.distanceBetweenTwoPoints(pickupPoint, availableTaxi.getCurrentLocation());
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
        taxiDao.updateTaxi(taxiDto);
    }

    public void addTaxi(TaxiDto taxiDto) {
        taxiDao.addTaxi(taxiDto);
    }

    public List<TaxiDto> getAllTaxis() {
        return taxiDao.getAllTaxis();
    }
}
