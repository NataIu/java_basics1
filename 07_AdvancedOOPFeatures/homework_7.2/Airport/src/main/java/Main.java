import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Airport airport = Airport.getInstance();

//        airport.getTerminals().stream().map(Terminal::getFlights)
//                .flatMap(flights -> flights.stream())
//                .collect(Collectors.toList()).forEach(System.out::println);
//        System.out.println("-------------------------------------------------------");

        List<Flight> nextFlights = findPlanesLeavingInTheNextTwoHours(airport);
        nextFlights.stream().forEach(System.out::println);

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        Instant instant = Instant.now();
        Date startDate = Date.from(instant);
        Date endDate = Date.from(instant.plus(2, ChronoUnit.HOURS));

        List<Flight> nextFlights = airport.getTerminals().stream().map(Terminal::getFlights)
                            .flatMap(flights -> flights.stream())
                            .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                            .filter(flight -> flight.getDate().after(startDate) && flight.getDate().before(endDate))
                            .collect(Collectors.toList());

        return nextFlights;
    }

}