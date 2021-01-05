public class TwoDimensionalArray {
    public static char symbol = 'X';

    public static char[][] getTwoDimensionalArray(int size) {


        char[][] resultArray = new char[size][size];
        for (int i = 0; i < size; i++) {
           fillOneLine(i,size,resultArray);
        }

        return resultArray;
    }

    private static void fillOneLine(int lineNumber, int size, char[][] resultArray) {
        for (int i = 0; i < size; i++) {
            if ((lineNumber==i) || (lineNumber==size-i-1)) {
                resultArray[lineNumber][i] = 'X';
            }
            else {
                resultArray[lineNumber][i] = ' ';
            }
        }
    }
}
