public class Main {

  public static void main(String[] args) {

    String currentWord;
    boolean isNumber;
    int i;
    int summ = 0;

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    text = text.trim();

    while (text.length() > 0) {

      //отрежем первое слово от строки
      if (text.indexOf(' ') >= 0) {
        currentWord = text.substring(0, text.indexOf(' '));
        text = text.substring(text.indexOf(' ')).trim();
      } else {
        currentWord = text;
        text = "";
      }

      //проверим, является ли слово числом. Если является - добавим к общей сумме
      isNumber = true;
      i = 0;
      while (i < currentWord.length() && isNumber) {
        if (! Character.isDigit(currentWord.charAt(i))) {
          isNumber = false;
        }
        i++;
      }
      summ= summ+ (isNumber ? Integer.parseInt(currentWord) :0 );
    }
    System.out.println(""+summ);

  }
}