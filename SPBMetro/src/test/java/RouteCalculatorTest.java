import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class RouteCalculatorTest extends TestCase
{
    List<Station> route = new ArrayList<>();
    List<Station> route1 = new ArrayList<>();
    StationIndex stationIndex = new StationIndex();

    Line line1 = new Line(1,"Фрунзенско-Приморская");
    Line line2 = new Line(2,"Кировско-Выборгская");
    Line line3 = new Line(3,"Невско-Василеостровская");

    Station station1 = new Station("Звенигородская", line1) ;
    Station station2 = new Station("Пушкинская", line2);
    Station station3 = new Station("Владимирская", line2);
    Station station4 = new Station("Площадь восстания", line2);
    Station station5 = new Station("Маяковская", line3);
    Station station6 = new Station("Гостиный двор", line3);
    Station station7 = new Station("Василеостровская", line3);


    @Override
    protected void setUp() throws Exception {
        line1.addStation(station1);
        line2.addStation(station2);
        line2.addStation(station3);
        line2.addStation(station4);
        line3.addStation(station5);
        line3.addStation(station6);
        line3.addStation(station7);

        route.add(station1);
        route.add(station2);
        route.add(station3);
        route.add(station4);
        route.add(station5);
        route.add(station6);
        route.add(station7);

        route1.add(station1);
        route1.add(station2);

        ArrayList<Station> connect1 = new ArrayList<>();
        ArrayList<Station> connect2 = new ArrayList<>();

        connect1.add(station1);
        connect1.add(station2);
        connect2.add(station4);
        connect2.add(station5);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.addStation(station1);
        stationIndex.addStation(station2);
        stationIndex.addStation(station3);
        stationIndex.addStation(station4);
        stationIndex.addStation(station5);
        stationIndex.addStation(station6);
        stationIndex.addStation(station7);
        stationIndex.addConnection(connect1);
        stationIndex.addConnection(connect2);

        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);

    }

    public void testGetRouteOnTheLine(){
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        Station from = stationIndex.getStation("Маяковская",3);
        Station to = stationIndex.getStation("Василеостровская",3);
        List<Station> actual = routeCalculator.getShortestRoute(from,to);
        List<Station> expected = Arrays.asList(route.get(4),route.get(5),route.get(6));
        assertEquals(expected, actual);
    }

    public void testGetRouteWithOneConnection(){
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        Station from = stationIndex.getStation("Пушкинская",2);
        Station to = stationIndex.getStation("Маяковская",3);
        List<Station> actual = routeCalculator.getShortestRoute(from,to);
        List<Station> expected = Arrays.asList(route.get(1),route.get(2),route.get(3),route.get(4));
        assertEquals(expected, actual);
    }

    public void testGetRouteViaConnectedLine(){
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        Station from = stationIndex.getStation("Звенигородская",1);
        Station to = stationIndex.getStation("Пушкинская",2);
        List<Station> actual = routeCalculator.getShortestRoute(from,to);
        List<Station> expected = Arrays.asList(route.get(0),route.get(1));
        assertEquals(expected, actual);
    }

    public void testGetRouteWithTwoConnections(){
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        Station from = stationIndex.getStation("Звенигородская",1);
        Station to = stationIndex.getStation("Василеостровская",3);
        List<Station> actual = routeCalculator.getShortestRoute(from,to);
        List<Station> expected = Arrays.asList(route.get(0),route.get(1),route.get(2),route.get(3),route.get(4),route.get(5),route.get(6));
        assertEquals(expected, actual);
    }

    public void testCalculateDuration(){
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 17;
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute(){
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        Station from = stationIndex.getStation("Звенигородская",1);
        Station to = stationIndex.getStation("Василеостровская",3);
        List<Station> actual = routeCalculator.getShortestRoute(from,to);
        List<Station> expected = Arrays.asList(route.get(0),route.get(1),route.get(2),route.get(3),route.get(4),route.get(5),route.get(6));
        assertEquals(expected, actual);
    }

    public void testIsConnected(){
        double actual = RouteCalculator.calculateDuration(route1);
        double expected = 3.5;
        assertEquals(expected, actual);
    }

}
