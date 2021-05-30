package main.java;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer extends Thread{

    private File[] files;
    private String dstFolder;
    private int newWidth;
    private long start;

    public ImageResizer(File[] files, String dstFolder, int newWidth, long start) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.newWidth = newWidth;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth)
                );

                BufferedImage newImage = Scalr.resize(image,Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, newWidth,newHeight);

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Finished: "+ (System.currentTimeMillis() - start) +" ms");

    }
}
