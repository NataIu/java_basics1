public class Main {

    public static void main(String[] args) {
        searchAndReplaceDiamonds("fdfsfjk<", "*!");

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений

        text = text.replaceAll("<[^<>]*>", placeholder);
        System.out.println(text);
        return text;
    }

}