public class Main {

    public static void main(String[] args) {
        CardAccount bankAccount = new CardAccount();
        CardAccount secondAccount = new CardAccount();
//        CardAccount bankAccount = new CardAccount();
//        DepositAccount bankAccount = new DepositAccount();

        bankAccount.put(100000);
        System.out.println("first: "+bankAccount.getAmount()+", second: "+secondAccount.getAmount());

        bankAccount.send(secondAccount,100000);
        System.out.println("first: "+bankAccount.getAmount()+", second: "+secondAccount.getAmount());

//        bankAccount.put(10000);
//        System.out.println(bankAccount.getAmount());
//
//        bankAccount.put(-1);
//        System.out.println(bankAccount.getAmount());
//
//
//        bankAccount.take(100);
//        System.out.println(bankAccount.getAmount());

    }
}
