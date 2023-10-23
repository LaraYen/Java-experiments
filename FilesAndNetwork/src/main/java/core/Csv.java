package core;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//Класс парсинга CSV
public class Csv {

    protected static ArrayList<StationsCsv> stationsCsv = new ArrayList<>();
    //Вывод данных файла
    public static String getCsvFile (String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append("\n" + line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    public static void parseStations (String path){
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.remove(lines.get(0));
            lines.forEach(line -> {
                String[] fragments = line.split(",");
                String dateFormat = "dd.MM.yyyy";
                try {
                    if (!fragments[0].equals("name") && !fragments[1].equals("date")){
                        stationsCsv.add(new StationsCsv(fragments[0],(new SimpleDateFormat(dateFormat)).parse(fragments[1])));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
