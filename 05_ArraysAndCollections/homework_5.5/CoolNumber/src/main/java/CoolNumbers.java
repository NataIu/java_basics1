import java.util.*;

public class CoolNumbers {

    private final static int MAX_REGION = 199;
    private final static int MAX_DIGIT = 9;
    private final static String LETTERS_FOR_NUMBER = "АВЕКМНОРСТУХ";

    public static List<String> generateCoolNumbers() {

        ArrayList<String> coolNumbers = new ArrayList<>();
        for (int digit = 1; digit <= MAX_DIGIT; digit++) {

            for (int region = 1; region <= MAX_REGION; region++) {
                generateCoolNumbersForDigitAndRegion(coolNumbers,digit,region);
            }
        }

        return coolNumbers;
    }

    private static void generateCoolNumbersForDigitAndRegion(ArrayList<String> coolNumbers, int digit, int region) {

        int length = LETTERS_FOR_NUMBER.length();
        String currentNumber = "";

        for (int firstLetterNumber = 0; firstLetterNumber < length; firstLetterNumber++) {
            for (int secondLetterNumber = 0; secondLetterNumber < length; secondLetterNumber++) {
                for (int thirdLetterNumber = 0; thirdLetterNumber < length; thirdLetterNumber++) {
                    currentNumber = ""+LETTERS_FOR_NUMBER.charAt(firstLetterNumber)+digit+digit+digit+LETTERS_FOR_NUMBER.charAt(secondLetterNumber)+
                            LETTERS_FOR_NUMBER.charAt(thirdLetterNumber)+region;
                    coolNumbers.add(currentNumber);
                }
            }
        }
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {

//        int index = list.indexOf(number);
//        boolean result =  index >= 0 ? true: false;

        long start = System.nanoTime();
        boolean result = list.contains(number);
        long finish = System.nanoTime();
        long elapsed = finish - start;
         printMessage("Поиск перебором",result,elapsed);

        return result;
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {

        long start = System.nanoTime();
        int index = Collections.binarySearch(sortedList,number);
        long finish = System.nanoTime();
        long elapsed = finish - start;
        boolean result =  index >= 0 ? true: false;
        printMessage("Бинарный поиск",result,elapsed);

        return result;
    }

    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {

        long start = System.nanoTime();
        boolean result = hashSet.contains(number);
        long finish = System.nanoTime();
        long elapsed = finish - start;
        printMessage("Поиск в HashSet",result,elapsed);

        return result;
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {

        long start = System.nanoTime();
        boolean result = treeSet.contains(number);
        long finish = System.nanoTime();
        long elapsed = finish - start;
        printMessage("Поиск в TreeSet",result,elapsed);

        return result;
    }

    private static void printMessage(String startText, boolean result, long elapsed) {
        String text = startText + ": номер "+ (result ? "": "не ")+ "найден, поиск занял " + elapsed + "нс";
        System.out.println(text);
    }

}
