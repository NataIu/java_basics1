import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BankOperation extends Thread {

    Bank bank;
    List<BankTransaction> bankTransactions;

    @Override
    public void run() {

        try {
            for (BankTransaction bankTransaction : bankTransactions) {
//                System.out.println(this.getName()+ ": sum before= "+bank.getSumAllAccounts()+", tr: "+ bankTransaction.toString());
                bank.transfer(bankTransaction.getFromAccountNum(), bankTransaction.getToAccountNum(), bankTransaction.getValue());
//                System.out.println(this.getName()+ ": SUM AFTER= "+bank.getSumAllAccounts()+", tr: "+ bankTransaction.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        bank.getAccounts().values().forEach(s->System.out.println(this.getName() + " - "+ s));
        System.out.println(this.getName() + " - "+ bank.getSumAllAccounts());
    }
}
