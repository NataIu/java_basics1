public class Main {

  public static void main(String[] args) {
//    calculateSalarySum("  Маша - 100, Оля - 250, Коля 8  ");
        calculateSalarySum("  Маша  ");
  }

  public static int calculateSalarySum(String text){
    //TODO: реализуйте

    int result = 0;

    text = text.replaceAll("[^0-9 ]","").trim();
    if (text.length() > 0) {
      String[] summs = text.split("\\s+");

      for (int i = 0; i < summs.length; i++) {
        result += Integer.parseInt(summs[i]);
      }
    }

    System.out.println(result);
    return result;
  }

}