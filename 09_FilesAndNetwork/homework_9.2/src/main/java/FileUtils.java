
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileUtils {

    static Logger logger = LogManager.getLogger(FileUtils.class);

    public static void copyFolder(String sourceDirectory, String destinationDirectory) {

        CharSequence source = (CharSequence) sourceDirectory;
        CharSequence destination = (CharSequence) destinationDirectory;

        try {
            Files.walk(Path.of(sourceDirectory)).forEach(s -> {
                try {
                    Files.copy(s, changePath(source, destination, s));
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            });
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private static Path changePath(CharSequence source, CharSequence destination, Path currentFile) {

        String tmp = currentFile.toString();
        return Path.of(tmp.replace(source, destination));

    }
}
