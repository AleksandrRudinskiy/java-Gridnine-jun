package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilter {
    public List<Flight> departureAfterCurrentTime(List<Flight> flights) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return flights.stream()
                .filter(f -> f.getSegments().get(0).getDepartureDate().isAfter(currentDateTime))
                .collect(Collectors.toList());
    }

    public long findEarthTotalTime(Flight flight) {
        List<Segment> segments = flight.getSegments();
        long summDifference = 0;
        for (int i = 1; i < segments.size(); i++) {
            long differenceInMinutes = ChronoUnit.MINUTES.between(segments.get(i - 1).getArrivalDate(),
                    segments.get(i).getDepartureDate());
            summDifference += differenceInMinutes;
        }
        return summDifference;
    }

    public List<Flight> findFlightsLessTwoHours(List<Flight> flightList) {
        List<Flight> resultList = new ArrayList<>();
        for (Flight flight : flightList) {
            if (findEarthTotalTime(flight) < 120) {
                resultList.add(flight);
            }
        }
        return resultList;
    }

    public List<Flight> getWrongFlights(List<Flight> flightList) {
        List<Flight> wrongFlights = new ArrayList<>();

        for (Flight flight : flightList) {
            for (Segment segment : flight.getSegments()) {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    wrongFlights.add(flight);
                    break;
                }
            }
        }
        return wrongFlights;
    }

    public List<Flight> getFlightsWithoutWrongFlights(List<Flight> flightList) {
        List<Flight> flights = new LinkedList<>(flightList);
        List<Flight> wrongFlights = new LinkedList<>(getWrongFlights(flightList));
        flights.removeAll(wrongFlights);
        return flights;
    }
}
