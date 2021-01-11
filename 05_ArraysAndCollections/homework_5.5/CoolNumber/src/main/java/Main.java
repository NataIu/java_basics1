
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class Main {
    private static List<String> coolNumbers;
    private static HashSet<String> hashSetCoolNumbers;
    private static TreeSet<String> treeSetCoolNumbers;
    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска введимого номера в консоль в каждой из структуры данных
     - проанализоровать полученные данные
     */

    public static void main(String[] args) {

        coolNumbers = CoolNumbers.generateCoolNumbers();
        Collections.sort(coolNumbers);

        hashSetCoolNumbers = new HashSet<String>(coolNumbers);
        treeSetCoolNumbers = new TreeSet<String>(coolNumbers);

        doTest("А111АА1"); //в начале
        doTest("Л555МН102"); // в середине
        doTest("Х999ХХ199"); //в конце
        doTest("Х999ХХ000"); //не найден

    }

    private static void doTest(String number) {

        System.out.println("number = "+number);

        CoolNumbers.bruteForceSearchInList(coolNumbers,number);
        CoolNumbers.binarySearchInList(coolNumbers,number);
        CoolNumbers.searchInHashSet(hashSetCoolNumbers,number);
        CoolNumbers.searchInTreeSet(treeSetCoolNumbers,number);

        System.out.println();
    }
}
