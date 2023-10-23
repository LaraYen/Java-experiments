import core.Csv;
import core.Html;
import core.Json;

import org.json.simple.parser.ParseException;

import java.io.IOException;

import static core.CrateJson.*;
import static core.Json.*;
import static core.QueryFiles.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Html html = new Html("https://skillbox-java.github.io/");
        html.getLines();              //Парсинг линий метро из HTML в объект Line
        html.getStations();           //Парсинг станций метро из HTML в объект Station

        queryFilesCsv("C:\\Users\\Alino\\Desktop"); //Метод поиска файлов CSV

        listFilesCsv.forEach(Csv::parseStations); //Парсинг данных из CSV файлов

        queryFilesJson("C:\\Users\\Alino\\Desktop"); //Метод поиска файлов JSON
        listFilesJson.forEach(filePath -> {
            Json.parseStations(getJsonFile(filePath));    //Парсинг данных из JSON файлов
        });

        createJsonFileMap("C:\\Users\\Alino\\Documents\\map.json"); //Создание файла map.json
        createJsonText2("C:\\Users\\Alino\\Documents\\stations.json"); //Создание файла stations.json

    }
}
