package com.g3examples.calltaxi;

import com.g3examples.calltaxi.view.MainView;

public class Main {

    public static void main(String[] args) {
        MainView mainView = new MainView();
        System.out.println("Initializing Application Data...");
        mainView.initializeApplication();
        System.out.println("App Initialization Completed!");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Welcome to Taxi Booking Application (developed in Java at GoGetterGeeks!)");
        mainView.menu();
    }
}
