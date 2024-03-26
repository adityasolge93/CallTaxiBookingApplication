package com.g3examples.calltaxi.dao;

import com.g3examples.calltaxi.models.BookingDto;

import java.util.List;

public interface BookingDaoInterface {
    void addBooking(BookingDto bookingDto);

    List<BookingDto> getAllBookings();
}
