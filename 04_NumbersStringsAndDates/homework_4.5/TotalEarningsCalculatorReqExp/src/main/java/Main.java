public class Main {

  public static void main(String[] args) {
    calculateSalarySum("  Маша - 100, Оля - 250, Коля 8  ");
  }

  public static int calculateSalarySum(String text){

    int result = 0;

    String[] summs = text.split("[\\p{Punct} ]+");
    for (int i = 0; i < summs.length; i++) {
      result += (summs[i].matches("[0-9]+") ? Integer.parseInt(summs[i]) : 0);
    }

    System.out.println(result);
    return result;
  }

}