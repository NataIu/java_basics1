import java.io.File;
import java.util.ArrayList;

public class Main {

    public static final int MAX_ACCOUNT_NUMBER = 50;
    public static final long DEFAULT_MONEY_VALUE = 1000000;

    public static final int THREAD_VALUE = 10;
    public static final int THREAD_COUNT = 10;

    public static void main(String[] args) throws BankOperationException, InterruptedException {

        Bank bank = new Bank();


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
            bankOperation.start();

        }

        System.out.println(bank.getSumAllAccounts());

    }

    public static long getRandomSum() {
        return (long) (Math.random() * DEFAULT_MONEY_VALUE/15);
    }

    public static String getRandomAccountNumber() {
        return String.valueOf((int) (Math.random() * MAX_ACCOUNT_NUMBER));
    }

}
