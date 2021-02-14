import java.nio.file.Files;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String sourceDirectory = scanner.nextLine();
        String destinationDirectory = scanner.nextLine();
        FileUtils.copyFolder(sourceDirectory,destinationDirectory);
    }
}
