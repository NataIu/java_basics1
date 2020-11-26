public class Main {

  public static void main(String[] args) {

  }

  public static String splitTextInToWords(String text) {

    String[] words = text.replaceAll("[^a-zA-Z’ ]"," ").trim().split("\\s+");
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < words.length; i++) {
      builder.append(words[i]);
      if (i != words.length -1) {
        builder.append(System.lineSeparator());
      }
    }

    return builder.toString();
  }

}