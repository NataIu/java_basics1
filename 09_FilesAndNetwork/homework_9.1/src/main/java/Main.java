
import java.util.Scanner;
import org.apache.logging.log4j.*;

public class Main {

    private static final String BYTES = " б";
    private static final String KILOBYTES = " Кб";
    private static final String MEGABYTES = " Мб";
    private static final String GIGABYTES = " Гб";

    public static void main(String[] args)  {

        //        получать через консоль путь от пользователя до папки;
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        long commonSize = FileUtils.calculateFolderSize(fileName);

        //выводить полученную сумму файлов в удобочитаемом виде — в байтах, килобайтах, мегабайтах или гигабайтах;
        System.out.println("Размер папки "+fileName+" cоставляет "+sizeToText(commonSize));

    }

    private static String sizeToText(long commonSize) {
        if (commonSize < 1024 ) {
            return ""+commonSize+BYTES;
        }
        else  if (commonSize < 1024*1024 ) {
            return ""+commonSize/1024+KILOBYTES;
        }
        else  if (commonSize < 1024*1024*1024 ) {
            return ""+commonSize/1024/1024+MEGABYTES;
        }
        else {
            return ""+commonSize/1024/1024/1024+GIGABYTES;
        }
    }
}
