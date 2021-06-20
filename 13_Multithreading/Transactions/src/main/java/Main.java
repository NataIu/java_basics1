import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final int MAX_ACCOUNT_NUMBER = 30;
    public static final long DEFAULT_MONEY_VALUE = 5000;

    public static final int THREAD_VALUE = 10;
    public static final int THREAD_COUNT = 10;

    public static void main(String[] args) throws BankOperationException, InterruptedException {

        Bank bank = new Bank();
        List<BankOperation> bankOperationList = new ArrayList<>();


        for (int i = 0; i < MAX_ACCOUNT_NUMBER; i++) {
            bank.addAccount(new Account(DEFAULT_MONEY_VALUE,  Integer.toString(i),false));
        }
        System.out.println(bank.getSumAllAccounts());

        for (int i = 0; i < THREAD_COUNT; i++) {
            ArrayList<BankTransaction> bankTransactions = new ArrayList();
            for (int j = 0; j < THREAD_VALUE; j++) {
                bankTransactions.add(new BankTransaction(getRandomAccountNumber(),getRandomAccountNumber(), getRandomSum()));
            }
            BankOperation bankOperation = new BankOperation(bank, bankTransactions);
            bankOperationList.add(bankOperation);
            bankOperation.start();

        }

        try {
            for (BankOperation operation: bankOperationList) {
                operation.join();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(bank.getSumAllAccounts());
        bank.getAccounts().values().forEach(s-> System.out.println(s.toString()));

    }

    public static long getRandomSum() {
        return (long) (Math.random() * DEFAULT_MONEY_VALUE);
    }

    public static String getRandomAccountNumber() {
        return String.valueOf((int) (Math.random() * MAX_ACCOUNT_NUMBER));
    }

}
