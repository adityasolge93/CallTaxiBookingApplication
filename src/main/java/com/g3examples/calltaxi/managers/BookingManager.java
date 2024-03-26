package com.g3examples.calltaxi.managers;

import com.g3examples.calltaxi.dao.BookingDaoInterface;
import com.g3examples.calltaxi.dao.PickDropPointsDaoInterface;
import com.g3examples.calltaxi.models.BookingDto;
import com.g3examples.calltaxi.models.TaxiDto;

import java.util.List;

public class BookingManager {
    private static final double BASE_FARE = 100.0;
    private static final double PER_KM_FARE = 10.0;
    private static final int BASE_FARE_THRESHOLD_KM = 5;

    private static int bookingSequence = 0;

    private PickDropPointsDaoInterface pickDropPointsDao;
    private TaxiManager taxiManager;
    private BookingDaoInterface bookingDao;

    public BookingManager(BookingDaoInterface bookingDao, TaxiManager taxiManager, PickDropPointsDaoInterface pickDropPointsDao) {
        this.bookingDao = bookingDao;
        this.taxiManager = taxiManager;
        this.pickDropPointsDao = pickDropPointsDao;
    }

    public void createBooking(String customerId, String pickupPoint, String dropPoint, int pickupTime) {
        if (pickupPoint.equals(dropPoint)) {
            throw new RuntimeException("Pickup and Drop points cannot be same");
        }

        if (pickupTime < 0 || pickupTime > 24) {
            throw new RuntimeException("Invalid pickup time, must be between 0-24");
        }

        TaxiDto allottedTaxi = taxiManager.getTaxiAtPickupPoint(pickupPoint, pickupTime);
        if (allottedTaxi == null) {
            System.out.println("Booking cannot be completed, no taxi is available currently");
            return;
        }
        System.out.println("Taxi can be allotted");

        double fare;
        int distance = pickDropPointsDao.distanceBetweenTwoPoints(pickupPoint, dropPoint);
        if (distance <= BASE_FARE_THRESHOLD_KM) {
            fare = BASE_FARE;
        } else {
            fare = BASE_FARE + ((distance - BASE_FARE_THRESHOLD_KM) * PER_KM_FARE);
        }
        int travelDurationInHour = distance/15;

        // Create booking
        BookingDto bookingDto = new BookingDto();
        bookingDto.setBookingId(String.valueOf(++bookingSequence));
        bookingDto.setCustomerId(customerId);
        bookingDto.setTaxiNo(allottedTaxi.getTaxiNo());
        bookingDto.setPickupPoint(pickupPoint);
        bookingDto.setDropPoint(dropPoint);
        bookingDto.setPickupTime(pickupTime);
        bookingDto.setDropTime(pickupTime + travelDurationInHour);
        bookingDto.setFare(fare);
        bookingDao.addBooking(bookingDto);

        // Update taxi details
        allottedTaxi.setEarnings(allottedTaxi.getEarnings() + fare);
        allottedTaxi.setCurrentLocation(dropPoint);
        allottedTaxi.setAvailableAtTime(bookingDto.getDropTime());
        taxiManager.updateTaxi(allottedTaxi);

        System.out.println(allottedTaxi.getTaxiNo() + " is allotted");
    }

    public List<BookingDto> getBookingsByTaxiNo(String taxiNo) {
        List<BookingDto> allBookings = bookingDao.getAllBookings();
        return allBookings.stream()
                .filter(bookingDto -> bookingDto.getTaxiNo().equals(taxiNo))
                .toList();
    }
}
