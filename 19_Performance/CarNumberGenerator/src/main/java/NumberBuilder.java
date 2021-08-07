import lombok.AllArgsConstructor;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

@AllArgsConstructor
public class NumberBuilder extends Thread{


    int startRegionCode;
    int endRegionCode;
    int threadNumber;

    @Override
    public void run() {

        StringBuilder builder;
        PrintWriter writer = null;
        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        try {
            writer = new PrintWriter("res/numbers_"+threadNumber+".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int regionCode = startRegionCode; regionCode <= endRegionCode; regionCode++) {
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

    }

    private static String padNumber(String numberStr, int numberStringLength, int numberLength) {
        int padSize = numberLength - numberStringLength;
        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }

}
