package com.gridnine.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class FlightFilterTest {

    private final List<Flight> flightList = FlightBuilder.createFlights();
    private final FlightFilter flightFilter = new FlightFilter();

    @Test
    public void testToString() {
        Flight flight = new Flight(
                List.of(new Segment(
                        LocalDateTime.of(2024, 1, 1, 15, 15, 0),
                        LocalDateTime.of(2024, 1, 1, 17, 16)))
        );
        String expected = "[2024-01-01T15:15|2024-01-01T17:16]";
        Assertions.assertEquals(expected, flight.toString());
    }

    @Test
    void shouldBe6WhenGetAllFlights() {
        int expectedFlightsCount = 6;
        int flightsCount = flightList.size();
        Assertions.assertEquals(expectedFlightsCount, flightsCount);
    }

    @Test
    void shouldBe5WhenCurrentTimeIs() {
        List<Flight> resulFlights = flightFilter.departureAfterCurrentTime(flightList);
        Assertions.assertEquals(5, resulFlights.size(),
                "Фильтр по времени вылета не верен.");
    }

    @Test
    void shouldBe4WhenEarthTimeIsLessThanTwoHours() {
        List<Flight> resulFlights = flightFilter.findFlightsLessTwoHours(flightList);
        Assertions.assertEquals(4, resulFlights.size(),
                "Фильтр по суммарному времени между прилетом" +
                        "одного сегмента и вылетом следующего не верна.");
    }

    @Test
    void shouldBe1WhenApplyAllFilters() {
        List<Flight> flights = flightFilter.findFlightsLessTwoHours(
                flightFilter.departureAfterCurrentTime(
                        flightFilter.getFlightsWithoutWrongFlights(flightList)));
        int expectedFlightsCount = 2;
        Assertions.assertEquals(expectedFlightsCount, flights.size());
    }

}
