public class Main {

  public static void main(String[] args) {


    String text = "Everyone could notice that people often complain and sa.";

    System.out.println(splitTextInToWords(text));
  }

  public static String splitTextInToWords(String text) {


    StringBuilder builder = new StringBuilder();
    String pattern = "[\\p{Punct}0-9 -]+";
    // отдельно обработаем начало и конец строки
    text = text.replaceAll("^"+pattern, "");
    text = text.replaceAll(pattern+"$", "");
    //а теперь ве остальное
    text = text.replaceAll(pattern, System.lineSeparator());
    return text;
  }

}