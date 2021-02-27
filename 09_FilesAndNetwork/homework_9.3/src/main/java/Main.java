public class Main {

    public static final String FILE_PATH = "src/main/resources/movementList.csv";


    public static void main(String[] args) {

        Movements movements = new Movements(FILE_PATH);
        System.out.println("Сумма расходов: " + movements.getExpenseSum());
        System.out.println("Сумма доходов: " + movements.getIncomeSum());
        System.out.println("Сумма расходов по организациям: ");
        movements.getOutcomeSumByCompanies().entrySet().stream().forEach(s -> {
            System.out.println(s.getKey() + "\t" + s.getValue());
        });

    }
}
