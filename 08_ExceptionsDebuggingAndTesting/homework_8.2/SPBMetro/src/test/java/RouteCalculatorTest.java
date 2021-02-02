import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class RouteCalculatorTest extends TestCase {

    StationIndex stationIndex;
    RouteCalculator routeCalculator;

    @Override
    protected void setUp() throws Exception {

        stationIndex = new StationIndex();


        //create lines
        Stream.of(1, 2, 3).forEach(lineNumber -> stationIndex.addLine(new Line(lineNumber, "line " + lineNumber)));

        //create stations
        Stream.of(1, 2, 3, 4, 5).forEach(stationNumber ->
                Stream.of(1, 2, 3).forEach(lineNumber ->
                        {
                            Line line = stationIndex.getLine(lineNumber);
                            Station station = new Station("station " + lineNumber + " - " + stationNumber, line);
                            stationIndex.addStation(station);
                            line.addStation(station);
                        }
                ));


        //create connections for different lines
        ArrayList<Station> connections1 = new ArrayList<Station>();
        connections1.add(stationIndex.getStation("station 1 - 2"));
        connections1.add(stationIndex.getStation("station 2 - 3"));

        ArrayList<Station> connections2 = new ArrayList<Station>();
        connections2.add(stationIndex.getStation("station 2 - 4"));
        connections2.add(stationIndex.getStation("station 3 - 4"));

        stationIndex.addConnection(connections1);
        stationIndex.addConnection(connections2);

        //create connections on one line
        Stream.of(1, 2, 3, 4).forEach(stationNumber ->
                Stream.of(1, 2, 3).forEach(lineNumber ->
                        {
                            ArrayList<Station> connections = new ArrayList<Station>();
                            connections.add(stationIndex.getStation("station " + lineNumber + " - " + stationNumber));
                            connections.add(stationIndex.getStation("station " + lineNumber + " - " + (stationNumber + 1)));
                            stationIndex.addConnection(connections);
                        }
                ));

        //route calculator
        routeCalculator = new RouteCalculator(stationIndex);

    }

    public void testGetRouteOnTheLine_OneStation() {

        Station beginStation = stationIndex.getStation("station 1 - 1");
        Station endStation = stationIndex.getStation("station 1 - 1");

        //expected
        ArrayList<Station> expected = new ArrayList<>();
        expected.add(beginStation);

        //actual
        List<Station> actual = routeCalculator.getShortestRoute(beginStation,endStation);

        assertEquals(expected,actual);
    }

    public void testGetRouteOnTheLine_DifferentStations() {

        Station beginStation = stationIndex.getStation("station 1 - 1");
        Station endStation = stationIndex.getStation("station 1 - 4");

        //expected
        ArrayList<Station> expected = new ArrayList<>();
        Stream.of(1,2,3,4).forEach(stationNumber -> expected.add(stationIndex.getStation("station 1 - "+stationNumber)));

        //actual
        List<Station> actual = routeCalculator.getShortestRoute(beginStation,endStation);

        assertEquals(expected,actual);
    }

    public void testGetRouteWithOneConnection_OneStation() {

        Station beginStation = stationIndex.getStation("station 1 - 2");
        Station endStation = stationIndex.getStation("station 2 - 3");

        //expected
        ArrayList<Station> expected = new ArrayList<>();
        expected.add(beginStation);
        expected.add(endStation);

        //actual
        List<Station> actual = routeCalculator.getShortestRoute(beginStation,endStation);

        assertEquals(expected,actual);
    }

    public void testGetRouteWithOneConnection_DifferentStations() {

        Station beginStation = stationIndex.getStation("station 1 - 1");
        Station endStation = stationIndex.getStation("station 2 - 5");

        //expected
        ArrayList<Station> expected = new ArrayList<>();
        Stream.of(1,2).forEach(stationNumber -> expected.add(stationIndex.getStation("station 1 - "+stationNumber)));
        Stream.of(3,4,5).forEach(stationNumber -> expected.add(stationIndex.getStation("station 2 - "+stationNumber)));

        //actual
        List<Station> actual = routeCalculator.getShortestRoute(beginStation,endStation);

        assertEquals(expected,actual);
    }

    public void testGetRouteWithTwoConnections_DifferentStations() {

        Station beginStation = stationIndex.getStation("station 1 - 1");
        Station endStation = stationIndex.getStation("station 3 - 2");

        //expected
        ArrayList<Station> expected = new ArrayList<>();
        Stream.of(1,2).forEach(stationNumber -> expected.add(stationIndex.getStation("station 1 - "+stationNumber)));
        Stream.of(3,4).forEach(stationNumber -> expected.add(stationIndex.getStation("station 2 - "+stationNumber)));
        Stream.of(4,3,2).forEach(stationNumber -> expected.add(stationIndex.getStation("station 3 - "+stationNumber)));

        //actual
        List<Station> actual = routeCalculator.getShortestRoute(beginStation,endStation);

        assertEquals(expected,actual);
    }


    public void testCalculateDuration_oneStation() {

        //route
        ArrayList route = new ArrayList<>();
        route.add(stationIndex.getStation("station 1 - 1"));

        double expected = 0;
        double actual = RouteCalculator.calculateDuration(route);

        assertEquals(expected,actual);

    }

    public void testCalculateDuration_ConnectedStstions() {

        //route
        ArrayList route = new ArrayList<>();
        Stream.of(1,2).forEach(stationNumber -> route.add(stationIndex.getStation("station 1 - "+stationNumber)));

        double expected = 2.5;
        double actual = RouteCalculator.calculateDuration(route);

        assertEquals(expected,actual);

    }

    public void testCalculateDuration_NeighbourStations() {

        //route
        ArrayList route = new ArrayList<>();
        route.add(stationIndex.getStation("station 1 - 2"));
        route.add(stationIndex.getStation("station 2 - 3"));

        double expected = 3.5;
        double actual = RouteCalculator.calculateDuration(route);

        assertEquals(expected,actual);

    }

    public void testCalculateDuration_DifferentStations() {

        //route
        ArrayList route = new ArrayList<>();
        Stream.of(1,2).forEach(stationNumber -> route.add(stationIndex.getStation("station 1 - "+stationNumber)));
        Stream.of(3,2,1).forEach(stationNumber -> route.add(stationIndex.getStation("station 2 - "+stationNumber)));

        double expected = 2.5+2.5+3.5+2.5;
        double actual = RouteCalculator.calculateDuration(route);

        assertEquals(expected,actual);

    }

}