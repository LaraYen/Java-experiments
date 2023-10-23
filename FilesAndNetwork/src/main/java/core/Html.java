package core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Класс парсинга HTML страницы
public class Html {
    private Document doc;
    public static final Map<String, Line> lines = new HashMap<>();
    public static ArrayList<Station> stations = new ArrayList<>();

    public Html(String url){
        try{
            this.doc = Jsoup.connect(url).get();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    //вывод HTML кода
    public void printHTML(){
        System.out.println(doc);
    }

    //Получение списка линий метро
    public void getLines(){
        Elements linesName = doc.select("span.js-metro-line");
        for (Element element : linesName) {
            lines.put(element.attr("data-line"), new Line(element.attr("data-line"), element.text()));
        }
    }

    //Вывод линий метро
    public void printLines(){
        this.getLines();
        lines.forEach((s, line) -> System.out.println(line.getNumber() + " " + line.getName()));
    }

    //Получение станций метро
    public void getStations(){
        Elements stationsNames = doc.select("div.t-metrostation-list-table");
        stationsNames.forEach(element -> {
            Elements stationsNames2 = element.select("span.name");
            stationsNames2.forEach(
                    element1 -> stations.add(new Station(lines.get(element.attr("data-line")),element1.text())));
            });
    }

    //Вывод станций метро
    public void printStations(){
        for (Station station : stations) {
            System.out.println(station.toString());
        }
    }

}
