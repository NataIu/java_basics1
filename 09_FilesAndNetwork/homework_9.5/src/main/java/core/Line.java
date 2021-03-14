package core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Line implements Comparable<Line>
{
    private String number;
    private String name;
//    private List<Station> stations;

    public Line(String number, String name)
    {
        this.number = number;
        this.name = name;
//        stations = new ArrayList<>();
    }

//    public void addStation(Station station)
//    {
//        stations.add(station);
//    }
//
//    public List<Station> getStations()
//    {
//        return stations;
//    }
//
    @Override
    public int compareTo(Line line)
    {
              return String.compareTo(number, line.getNumber());
    }

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Line) obj) == 0;
    }
}