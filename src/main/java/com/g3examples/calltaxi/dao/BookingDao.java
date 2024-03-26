package com.g3examples.calltaxi.dao;

import com.g3examples.calltaxi.models.BookingDto;

import java.util.ArrayList;
import java.util.List;

public class BookingDao implements BookingDaoInterface {
    private static final List<BookingDto> bookings = new ArrayList<>();

    public void addBooking(BookingDto bookingDto) {
        bookings.add(bookingDto);
    }

    public List<BookingDto> getAllBookings() {
        return new ArrayList<>(bookings);
    }
}
