package com.g3examples.calltaxi.dao;

import com.g3examples.calltaxi.models.TaxiDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxiDao implements TaxiDaoInterface {
    private static final Map<String, TaxiDto> taxiMap = new HashMap<>();

    public void addTaxi(TaxiDto taxiDto) {
        taxiMap.put(taxiDto.getTaxiNo(), taxiDto);
    }

    @Override
    public void updateTaxi(TaxiDto taxiDto) {
        taxiMap.put(taxiDto.getTaxiNo(), taxiDto);
    }

    public TaxiDto getTaxiDetails(String taxiNo) {
        return taxiMap.get(taxiNo);
    }

    public List<TaxiDto> getAllTaxis() {
        return taxiMap.values().stream().toList();
    }
}
