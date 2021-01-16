public class CardAccount extends BankAccount {

    private final static int COMMISSION_PERCENTAGE = 1;

    @Override
    public void take(double amountToTake) {
        double amountWithComission = amountToTake * (100+COMMISSION_PERCENTAGE)/100;
        super.take(amountWithComission);
    }

}
