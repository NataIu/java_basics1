public class Main {
    public static void main(String[] args) {

        char[][] twoDimensionalArray = TwoDimensionalArray.getTwoDimensionalArray(5);
        for (char[] line:twoDimensionalArray) {
            for (int i = 0; i < line.length; i++) {
                System.out.print(line[i]);
            }
            System.out.println();

        }
        //Распечатайте сгенерированный в классе TwoDimensionalArray.java двумерный массив
    }
}
