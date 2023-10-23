package core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Класс поиска файлов
public class QueryFiles {
    public static List<String> listFilesCsv = new ArrayList<>();
    public static List<String> listFilesJson = new ArrayList<>();

    public static void queryFilesCsv(String path) throws IOException {
        Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .map(Path::toFile).toList().forEach(file -> {
                    if (file.getName().endsWith(".csv")){
                        System.out.println(file);
                        listFilesCsv.add(file.toString());
                    }
                });

    }
    public static void queryFilesJson(String path) throws IOException {
        Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .map(Path::toFile).toList().forEach(file -> {
                    if (file.getName().endsWith(".json")){
                        System.out.println(file);
                        listFilesJson.add(file.toString());
                    }
                });
    }
}
