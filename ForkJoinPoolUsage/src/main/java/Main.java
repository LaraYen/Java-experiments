import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        String url = "https://skillbox.ru/";
        String pathToSiteMapFile = "data/siteMap.txt";
        SiteMap siteMap = new SiteMap(url);
        SiteMapRecursiveAction task = new SiteMapRecursiveAction(siteMap);
        new ForkJoinPool().invoke(task);

        try {
            FileOutputStream stream = new FileOutputStream(pathToSiteMapFile);
            String siteMapFile = createSiteMapString(siteMap, 0);
            stream.write(siteMapFile.getBytes());
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Метод для создания файла
    public static String createSiteMapString(SiteMap siteMap, int ident){
        String tab = String.join("", Collections.nCopies(ident, "\t"));
        StringBuilder result = new StringBuilder(tab + siteMap.getUrl());
        siteMap.getSiteMapChildrens().forEach(child -> result.append("\n")
                .append(createSiteMapString(child, ident + 1)));
        return result.toString();
    }
}
