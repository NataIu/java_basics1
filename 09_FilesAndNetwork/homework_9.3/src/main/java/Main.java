public class Main {

    public static final String filePath = "src/main/resources/movementList.csv";

    public static void main(String[] args) {

        Movements movements = new Movements(filePath);
        System.out.println("Сумма расходов: "+movements.getExpenseSum());
        System.out.println("Сумма доходов: "+movements.getIncomeSum());


//        Примеры работы программы
//
//        Сумма расходов: 398 563.39 руб.
//                Сумма доходов: 289 890.06 руб.
//
//                Суммы расходов по организациям:
//        RUSMOSKVA56  SHLOVE REPUBLIC        1 081.53 руб.
//                RUSMOSCOW42 SHCL ETOILE                     126.34 руб.
//                RUSPUSHKINO105ZOOMAGAZIN 4             217.65 руб.

    }
}
