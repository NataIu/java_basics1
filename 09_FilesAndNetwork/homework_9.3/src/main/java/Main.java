public class Main {

    public static final String filePath = "src/main/resources/movementList.csv";

    public static void main(String[] args) {

        Movements movements = new Movements(filePath);
        System.out.println("Сумма расходов: "+movements.getExpenseSum());
        System.out.println("Сумма доходов: "+movements.getIncomeSum());

    }
}
