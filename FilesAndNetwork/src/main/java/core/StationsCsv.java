package core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Объект Station спарсенный из CSV файла
public class StationsCsv {
    private final String name;
    private final Date date;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public StationsCsv(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return dateFormat.format(date);
    }

    @Override
    public String toString() {
            return "Станция: " + name + ", дата открытия: " + dateFormat.format(date);
    }
}
