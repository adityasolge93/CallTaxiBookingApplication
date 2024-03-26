package com.g3examples.calltaxi.view;

import com.g3examples.calltaxi.dao.BookingDao;
import com.g3examples.calltaxi.dao.BookingDaoInterface;
import com.g3examples.calltaxi.dao.PickDropPointsDao;
import com.g3examples.calltaxi.dao.PickDropPointsDaoInterface;
import com.g3examples.calltaxi.dao.TaxiDao;
import com.g3examples.calltaxi.dao.TaxiDaoInterface;
import com.g3examples.calltaxi.managers.BookingManager;
import com.g3examples.calltaxi.managers.TaxiManager;
import com.g3examples.calltaxi.models.BookingDto;
import com.g3examples.calltaxi.models.TaxiDto;

import java.util.List;
import java.util.Scanner;

public class MainView {
    private static final int CALL_TAXI_BOOKING_CHOICE = 1;
    private static final int DISPLAY_THE_TAXI_DETAILS_CHOICE = 2;
    private static final int EXIT_CHOICE = 3;

    private BookingDaoInterface bookingDao;
    private BookingManager bookingManager;
    private PickDropPointsDaoInterface pickDropPointsDao;
    private TaxiDaoInterface taxiDao;
    private TaxiManager taxiManager;

    public MainView() {
        pickDropPointsDao = new PickDropPointsDao();
        taxiDao = new TaxiDao();
        taxiManager = new TaxiManager(taxiDao, pickDropPointsDao);
        bookingDao = new BookingDao();
        bookingManager = new BookingManager(bookingDao, taxiManager, pickDropPointsDao);
    }


    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("1. Call Taxi Booking");
            System.out.println("2. Display the Taxi Details");
            System.out.println("3. Exit");
            System.out.println("====================");
            System.out.print("Enter Choice (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == CALL_TAXI_BOOKING_CHOICE) {
                System.out.print("Customer ID: ");
                String customerId = scanner.nextLine();

                System.out.print("Pickup Point: ");
                String pickupPoint = scanner.nextLine();

                System.out.print("Drop Point: ");
                String dropPoint = scanner.nextLine();

                System.out.print("Pickup Time: ");
                int pickupTime = scanner.nextInt();
                scanner.nextLine();

                bookingManager.createBooking(customerId, pickupPoint, dropPoint, pickupTime);
            } else if (choice == DISPLAY_THE_TAXI_DETAILS_CHOICE) {
                List<TaxiDto> taxis = taxiManager.getAllTaxis();
                for (TaxiDto taxi : taxis) {
                    System.out.println("Taxi No: " + taxi.getTaxiNo() + " | Total Earnings: " + taxi.getEarnings());
                    System.out.println("BookingID\tCustomerID\tFrom\tTo\tPickupTime\tDropTime\tAmount");
                    List<BookingDto> taxiBookings = bookingManager.getBookingsByTaxiNo(taxi.getTaxiNo());
                    for (BookingDto booking : taxiBookings) {
                        System.out.println(booking.getBookingId() + "\t" + booking.getCustomerId() +
                                "\t" + booking.getPickupPoint() + "\t" + booking.getDropPoint() +
                                "\t" + booking.getPickupTime() + "\t" + booking.getDropTime() +
                                "\t" + booking.getFare());
                    }
                    System.out.println("\n");
                }
            } else if (choice == EXIT_CHOICE) {
                System.out.println("------------------------------------------------");
                System.out.println("Thank you for using Call Taxi Booking Application");
                System.out.println("------------------------------------------------");
                break;
            }
        }
    }

    public void initializeApplication() {
        TaxiDto taxi1 = new TaxiDto("Taxi-1", 0.0, "A", -1);
        TaxiDto taxi2 = new TaxiDto("Taxi-2", 0.0, "A", -1);
        TaxiDto taxi3 = new TaxiDto("Taxi-3", 0.0, "A", -1);
        TaxiDto taxi4 = new TaxiDto("Taxi-4", 0.0, "A", -1);

        taxiManager.addTaxi(taxi1);
        taxiManager.addTaxi(taxi2);
        taxiManager.addTaxi(taxi3);
        taxiManager.addTaxi(taxi4);

        pickDropPointsDao.addPickDropPoint("A", 0);
        pickDropPointsDao.addPickDropPoint("B", 15);
        pickDropPointsDao.addPickDropPoint("C", 30);
        pickDropPointsDao.addPickDropPoint("D", 45);
        pickDropPointsDao.addPickDropPoint("E", 60);
        pickDropPointsDao.addPickDropPoint("F", 75);
    }
}
