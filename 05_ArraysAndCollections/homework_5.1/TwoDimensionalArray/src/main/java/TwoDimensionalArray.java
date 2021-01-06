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

    public static double[][] getTwoDimensionalArrayWithBumbers(int size) {

        double[][] resultArray = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                resultArray[i][j] = ((double) j)/10 + i;
            }
        }

        return resultArray;
    }

    public static void printValuesDiagonally(double[][] array) {

        //считаю, что массив n*n, т.к. array.length равно и длине, и высоте
        for (int i = 0; i < 2 * array.length - 1; i++) {
            printLine(i,array);
            System.out.println();
        }

    }

    private static void printLine(int lineIndex, double[][] array) {

        if (lineIndex < array.length) {
            for (int i = 0; i <= lineIndex; i++) {
                if (lineIndex % 2 == 1) {
                    System.out.print(array[i][lineIndex - i] + " ");
                } else {
                    System.out.print(array[lineIndex - i][i] + " ");
                }
            }
        }
        else {

            for (int i = (lineIndex - array.length + 1); i < array.length; i++) {
                if (lineIndex % 2 == 1) {
                    System.out.print(array[i][lineIndex - i] + " ");
                } else {
                    System.out.print(array[lineIndex - i] [i]+ " ");
                }
            }

        }

    }

}
