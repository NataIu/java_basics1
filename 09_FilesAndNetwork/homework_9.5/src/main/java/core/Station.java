package core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Station //implements Comparable<Station>
{
    private Line line;
    private String name;
    private List<Station> transitions;

    public Station(String name, Line line)
    {
        this.name = name;
        this.line = line;
    }


//
//    @Override
//    public int compareTo(Station station)
//    {
//        int lineComparison = line.compareTo(station.getLine());
//        if(lineComparison != 0) {
//            return lineComparison;
//        }
//        return name.compareToIgnoreCase(station.getName());
//    }
//
//    @Override
//    public boolean equals(Object obj)
//    {
//        return compareTo((Station) obj) == 0;
//    }

    @Override
    public String toString()
    {
        return name;
    }
}