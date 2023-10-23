import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;

public class Main {
    private static final int newWidth = 300;

    public static void main(String[] args) {
        try {
            int cores = Runtime.getRuntime().availableProcessors();
            System.out.println("Количество доступных ядер: " + cores);
            String srcFolder = "C:\\Users\\Alino\\Documents\\sourceImg";
            String dstFolder = "C:\\Users\\Alino\\Documents\\destImg";

            File srcDir = new File(srcFolder);

            long start = System.currentTimeMillis();

            File[] files = srcDir.listFiles();

            int middle = files.length / cores;
            int middleMod = files.length % cores;
            int lenghtArr;

            int step = 0;
            //Если картинок меньше, чем количество ядер
            if (middle == 0){
                for (int i = 0; i < middleMod; i++){
                    File[] files1 = new File[1];
                    System.arraycopy(files, i, files1, 0, files1.length);
                    ImageResizer resizer1 = new ImageResizer(files1, newWidth, dstFolder, start);
                    resizer1.start();
                }
            //Если картинок больше, чем количество ядер
            } else {
                for (int i = 0; i < cores; i++){
            //Высчитываем, есть ли остаток, если да, то добавляем еще одну картинку для равномерности распределения нагрузки между потоками
                    if (middleMod > 0){
                        lenghtArr = middle + 1;
                    } else {
                        lenghtArr = middle;
                    }
                    File[] files1 = new File[lenghtArr];
                    System.arraycopy(files, step, files1, 0, files1.length);
                    ImageResizer resizer1 = new ImageResizer(files1, newWidth, dstFolder, start);
                    resizer1.start();
                    step = step + lenghtArr;
                    middleMod--;
                    lenghtArr = 0;
                }
            }
            System.out.println("Duration: " + (System.currentTimeMillis() - start));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
