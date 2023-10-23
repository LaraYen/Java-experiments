package core;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//Класс парсинга JSON
public class Json {

    protected static ArrayList<StationsJson> stationsJson = new ArrayList<>();

    public static String getJsonFile (String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(builder::append);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    public static void parseFile (String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray stationsArray = (JSONArray) parser.parse(json);
    }

    public static void parseStations (String json){
        try {
            JSONParser parser = new JSONParser();
            JSONArray stationsArray = (JSONArray) parser.parse(json);
            stationsArray.forEach(stationObject -> {
                JSONObject stationJsonObject = (JSONObject) stationObject;
                stationsJson.add(new StationsJson(stationJsonObject.get("depth").toString(),stationJsonObject.get("station_name").toString()));
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
