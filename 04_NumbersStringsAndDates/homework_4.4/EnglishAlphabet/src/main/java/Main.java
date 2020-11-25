public class Main {

    public static void main(String[] args) {

        String englishAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < englishAlphabet.length(); i++) {
            char currentSymbol = englishAlphabet.charAt(i);
            System.out.println(currentSymbol+": "+(int) currentSymbol);
        }

    }
}
