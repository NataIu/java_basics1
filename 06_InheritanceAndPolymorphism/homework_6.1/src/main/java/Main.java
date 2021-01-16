public class Main {

    public static void main(String[] args) {
//        BankAccount bankAccount = new BankAccount();
//        CardAccount bankAccount = new CardAccount();
        DepositAccount bankAccount = new DepositAccount();

        bankAccount.put(100000);
        System.out.println(bankAccount.getAmount());

        bankAccount.put(10000);
        System.out.println(bankAccount.getAmount());

        bankAccount.put(-1);
        System.out.println(bankAccount.getAmount());


        bankAccount.take(100);
        System.out.println(bankAccount.getAmount());

    }
}
