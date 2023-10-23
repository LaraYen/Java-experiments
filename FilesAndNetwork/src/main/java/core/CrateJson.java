package core;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//Класс создания JSON файлов
public class CrateJson {
    private static final JSONObject object = new JSONObject();
    private static final JSONObject stationsFile = new JSONObject();


    public static void createJsonFileMap(String path) {
        JSONObject stat = new JSONObject();
        object.put("stations",stat);
        Html.lines.forEach((s, line) -> {
            JSONArray ar = new JSONArray();
            line.getStations().forEach(station -> ar.add(station.getName()));
            stat.put(line.getNumber(),ar);
        });
        JSONArray linArr = new JSONArray();
        object.put("lines",linArr);
        Html.lines.forEach((s, line) -> {
            JSONObject lin = new JSONObject();
            lin.put("number",line.getNumber());
            lin.put("name",line.getName());
            linArr.add(lin);
        });
        try {
            String json = object.toJSONString();
            byte[] bs = json.getBytes();
            Files.write(Path.of(path),bs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createJsonText2(String path){
        JSONArray ar = new JSONArray();
        stationsFile.put("stations",ar);
        Html.stations.forEach(station -> {
            JSONObject stat = new JSONObject();
            ar.add(stat);
            stat.put("name",station.getName());
            stat.put("line",station.getLine());
            Csv.stationsCsv.forEach(stationCsv -> {
                if (stationCsv.getName().equals(station.getName())){
                    stat.put("date",stationCsv.getDate());
                }
            });
            Json.stationsJson.forEach(stationsJson -> {
                if (stationsJson.getName().equals(station.getName()) && !stationsJson.getDepth().equals("?")){
                    stat.put("depth",stationsJson.getDepth());
                }
            });
        });
        try {
            String json = stationsFile.toJSONString();
            byte[] bs = json.getBytes();
            Files.write(Path.of(path),bs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
