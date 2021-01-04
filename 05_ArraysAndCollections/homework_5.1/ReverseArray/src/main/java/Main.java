public class Main {

    public static void main(String[] args) {
        String line = "Каждый охотник желает знать, где сидит фазан";
        String[] colorWords = line.split("\\p{Punct}?\\s");
        ReverseArray.reverse(colorWords);
        for (String colorWord:colorWords) {
            System.out.println(colorWord);
        }
    }
}
