import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    static Logger logger = LogManager.getLogger(FileUtils.class);

    public static long calculateFolderSize(String path)  {

        long result = 0;

        //получить размер всех файлов в указанной папке и ее подпапках,
        // реализация этого должна быть написана в методе calculateFolderSize() класса FileUtils;

        //программа должна перехватывать все исключения, возникающие при ошибках чтения файлов и папок,
        // и выводить сообщение об ошибке с трассировкой стека (stack trace).

        Path dirPath = Path.of(path);
        if (Files.isDirectory(dirPath)) {
            try {
                result = Files.walk(dirPath)
                        .filter(Files::isRegularFile)
                        .map(path1 -> {
                            try {
                                return Files.size(path1);
                            } catch (IOException e) {
                                logger.error(e.getMessage());
                            }
                            return null;
                        })
                        .reduce(Long::sum)
                        .orElse(0L);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return result;
    }
}
