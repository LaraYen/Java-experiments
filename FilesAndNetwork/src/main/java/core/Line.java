package core;

import java.util.ArrayList;
import java.util.List;

//Объект Line спарсенный из HTML страницы
public class Line {
    private final String name;
    private final String number;
    protected List<Station> stations;

    public Line(String number, String name){
        this.number = number;
        this.name = name;
        stations = new ArrayList<>();
    }

    public List<Station> getStations() {
        Html.stations.forEach(station -> {
            if (station.getLine().equals(this.getNumber())){
                this.stations.add(station);
            }
        });
        return stations;
    }

    public String getName(){
        return name;
    }

    public String getNumber(){
        return number;
    }

    @Override
    public String toString() {
        return number + ' ' + name;
    }

}
