
import java.util.Scanner;

public class Main {
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
            return ""+commonSize+" б";
        }
        else  if (commonSize < 1024*1024 ) {
            return ""+commonSize/1024+" Кб";
        }
        else  if (commonSize < 1024*1024*1024 ) {
            return ""+commonSize/1024/1024+" Мб";
        }
        else {
            return ""+commonSize/1024/1024/1024+" Гб";
        }
    }
}
