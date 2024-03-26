package com.g3examples.calltaxi.dao;

import com.g3examples.calltaxi.models.TaxiDto;

import java.util.List;

public interface TaxiDaoInterface {
    void addTaxi(TaxiDto taxiDto);
    void updateTaxi(TaxiDto taxiDto);

    TaxiDto getTaxiDetails(String taxiNo);

    List<TaxiDto> getAllTaxis();
}
