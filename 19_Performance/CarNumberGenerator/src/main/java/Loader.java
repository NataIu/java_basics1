import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

//        FileOutputStream writer = new FileOutputStream("res/numbers.txt");
        PrintWriter writer = new PrintWriter("res/numbers.txt");

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        StringBuilder builder = new StringBuilder();
        for (int regionCode = 1; regionCode < 100; regionCode++) {
            String regionCodeStr = Integer.toString(regionCode);
            int regionCodeStrLength = regionCodeStr.length();
            builder = new StringBuilder();
            for (int number = 1; number < 1000; number++) {
                String numberStr = Integer.toString(number);
                int numberStringLength = numberStr.length();
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            builder.append(firstLetter);
                            builder.append(padNumber(numberStr, numberStringLength,3));
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            builder.append(padNumber(regionCodeStr, regionCodeStrLength, 2));
                            builder.append("\n");
                        }
                    }
                }
            }
            writer.write(builder.toString());
        }

        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }



    private static String padNumber(String numberStr, int numberStringLength, int numberLength) {
        int padSize = numberLength - numberStringLength;
        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }
}
