
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;


public class FileUtils {

    static Logger logger = LogManager.getLogger(FileUtils.class);

    public static void copyFolder(String sourceDirectory, String destinationDirectory) {

        CharSequence source = (CharSequence) sourceDirectory;
        CharSequence destination = (CharSequence) destinationDirectory;
        StandardCopyOption copyOption;

        StringBuilder stringBuilder = getListOfExistingFiles(sourceDirectory, source, destination);
        copyOption = getCopyOption(stringBuilder);

        try {
            StandardCopyOption finalCopyOption = copyOption;
            Files.walk(Path.of(sourceDirectory)).forEach(s -> {
                try {
                    Files.copy(s, changePath(source, destination, s), finalCopyOption);
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            });
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private static StandardCopyOption getCopyOption(StringBuilder stringBuilder) {

        StandardCopyOption copyOption = StandardCopyOption.COPY_ATTRIBUTES;
        String answer = "";

        if (stringBuilder.length() > 0) {
            while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
                System.out.println("These files are already exist in destionation directory: ");
                System.out.println(stringBuilder);
                System.out.println("Replace files (y/n)?  ");

                Scanner scanner = new Scanner(System.in);
                answer = scanner.nextLine();
            }
            if (answer.equalsIgnoreCase("y")) {
                copyOption = StandardCopyOption.REPLACE_EXISTING;
            }
        }
        return copyOption;
    }

    private static StringBuilder getListOfExistingFiles(String sourceDirectory, CharSequence source, CharSequence destination) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            Files.walk(Path.of(sourceDirectory))
                    .filter(Files::isRegularFile)
                    .filter(s -> {
                        try {
                            Path newPath = changePath(source, destination, s);
                            return Files.exists(newPath) && ((Long) Files.size(s)).equals(((Long) Files.size(newPath)));
                        } catch (IOException e) {
                            logger.error(e.getMessage());
                        }
                        return false;
                    })
                    .forEach(s->stringBuilder.append(s.toString()+"\n"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return stringBuilder;
    }

    private static Path changePath(CharSequence source, CharSequence destination, Path currentFile) {

        String tmp = currentFile.toString();
        return Path.of(tmp.replace(source, destination));

    }
}
