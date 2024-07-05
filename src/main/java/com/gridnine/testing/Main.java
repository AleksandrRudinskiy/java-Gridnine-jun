package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilter();
        System.out.println("Все перелеты:");
        flights.forEach(System.out::println);

        System.out.println("Исключены вылеты до текущего момента времени:");
        flightFilter.departureAfterCurrentTime(flights).forEach(System.out::println);

        System.out.println("Исключены перелеты, где имеются сегменты с датой прилёта раньше даты вылета:");
        flightFilter.getFlightsWithoutWrongFlights(flights).forEach(System.out::println);

        System.out.println("Исключены перелеты где общее время, проведённое на земле превышает два часа: ");
        flightFilter.findFlightsLessTwoHours(flights).forEach(System.out::println);

        System.out.println("Перелеты, удовлетворяющие всем трем условиям:");
        flightFilter.findFlightsLessTwoHours(
                flightFilter.departureAfterCurrentTime(
                        flightFilter.getFlightsWithoutWrongFlights(flights))).forEach(System.out::println);


    }


}
