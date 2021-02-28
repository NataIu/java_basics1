import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    private static final String PATH = "https://lenta.ru";
    private static final String IMAGE_TAG = "img";
    private static final String FOLDER = "images/";

    private static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) throws IOException {
        getImagesFromWebSite(PATH);
    }

    public static void getImagesFromWebSite(String path) {
        Document doc = null;
        try {
            doc = Jsoup.connect(path).get();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        doc.select(IMAGE_TAG).stream()
                .map(s -> s.attr("abs:src"))
                .filter(s -> {
                    try {
                        return ((new URL(s)).openConnection().getContentType().matches(".*image.*"));
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    }
                    return false;
                })
                .map(s -> downloadFile(s))
                .forEach(System.out::println);

    }

    private static String downloadFile(String remoteFilePath) {

        String name = "";
        try {
            int index = remoteFilePath.lastIndexOf("/");
            name = remoteFilePath.substring(index, remoteFilePath.length());
            name = name.replaceAll("\\?", ""); //т.к. названия некоых карттинок его содержат

            URL url = new URL(remoteFilePath);
            InputStream in = url.openStream();

            if (! Files.exists(Path.of(FOLDER))) {
                Files.createDirectory(Path.of(FOLDER));
            }

            FileOutputStream outputStream = new FileOutputStream(FOLDER + name);
            OutputStream out = new BufferedOutputStream(outputStream);

            for (int b; (b = in.read()) != -1; ) {
                out.write(b);
            }
            out.flush();
            out.close();
            in.close();

            return name;
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return name;
    }
}
