import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

import static java.lang.Thread.sleep;

public class HTMLparse {

    //public List<String> linksList = new ArrayList<>();

    //Используем потокобезопасный класс для хранения ссылок
    private static ConcurrentSkipListSet<String> links;


    //Метод для поиска ссылок на странице
    public static ConcurrentSkipListSet<String> getLinks(String url){
        links = new ConcurrentSkipListSet<>();

        try {
            sleep(150);
            Connection connection = Jsoup.connect(url)
                    .ignoreHttpErrors(true)
                    .timeout(600)
                    .followRedirects(false);
            Document document = connection.get();
            Elements elements = document.select("body").select("a");
            for (Element element : elements){
                String link = element.absUrl("href");
                if (isLink(link) && !isFile(link)) {
                    links.add(link);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e + " - " + url);
        } catch (SocketTimeoutException e) {
            System.out.println(e + " - " + url);
        } catch (IOException e) {
            System.out.println(e + " - " + url);
        }
        return links;
    }

    //Проверяем, что это действительно ссылка
    public static boolean isLink(String link){
        String regex = "http[s]?://[^#,\\s]*\\.?skillbox\\.ru[^#,\\s]*";
        return link.matches(regex);
    }

    //Проверяем, что это не файл
    private static boolean isFile(String link){
        link.toLowerCase();
        return link.contains(".jpg")
                || link.contains("jpeg")
                || link.contains("png")
                || link.contains(".gif")
                || link.contains(".webp")
                || link.contains(".pdf")
                || link.contains(".eps")
                || link.contains(".xlsx")
                || link.contains(".doc")
                || link.contains(".pptx")
                || link.contains(".docx")
                || link.contains("?_ga");
    }

}
