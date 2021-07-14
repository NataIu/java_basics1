import main.java.ImageResizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "/Users/Nata/ForJava/src";
        String dstFolder = "/Users/Nata/ForJava/dst";
//        int cores = Runtime.getRuntime().availableProcessors();
        int cores = 10;
        System.out.println("cores: " + cores);

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();
        int finishPosition = 0;
        int startPosition = 0;
        int step = files.length / cores;
        for (int i = 0; i < cores; i++) {
            startPosition = finishPosition;
            if (i < cores) {
                finishPosition = startPosition + step;
            }
            else
            {
                finishPosition = files.length;
            }
            File[] tmpFiles = new File[finishPosition-startPosition];
            System.arraycopy(files,startPosition, tmpFiles,0, finishPosition-startPosition);
            ImageResizer tmpImageResizer = new ImageResizer(files,dstFolder,newWidth,start);
            tmpImageResizer.start();

        }


//        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
