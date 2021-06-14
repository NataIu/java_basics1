
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
public class Bank {

    @Getter
    private Map<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();
    private final static long VERIFICATION_AMOUNT = 50000;

    public void addAccount(Account account) {
        accounts.put(account.getAccNumber(), account);
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {

        Account accountTo = accounts.get(toAccountNum);
        Account accountFrom = accounts.get(fromAccountNum);


//            System.out.println("amount: "+amount+", from"+accountFrom+", to: "+ accountTo);

            try {
                verificateOperation(accountFrom, accountTo, amount);
                //synchronized делаю по Account.class, т.к. хочу завершить операцию на обоих счетах до передачи другому потоку
                if ((!accountTo.isBlocked()) && (!accountFrom.isBlocked())) {
                    synchronized (accountTo) {
                        accountTo.increaseMoney(amount);
                    }
                    synchronized (accountFrom) {
                    accountFrom.decreaseMoney(amount);
                    }
                }
            } catch (BankOperationException e) {
                e.printStackTrace();
            }


    }

    private void verificateOperation(Account accountFrom, Account accountTo, long amount) throws InterruptedException, BankOperationException {
        if (amount > VERIFICATION_AMOUNT) {
            if (isFraud(accountFrom.getAccNumber(), accountTo.getAccNumber(), amount)) {
                accountTo.setBlocked(true);
                accountFrom.setBlocked(true);
                throw new BankOperationException("Fraud transaction. Blocking accounts : " + accountFrom.getAccNumber() + ", " + accountTo.getAccNumber());
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
        return account.getMoney();
    }

    public long getSumAllAccounts() {
        return accounts.values().stream().mapToLong(Account::getMoney).sum();
    }
}
