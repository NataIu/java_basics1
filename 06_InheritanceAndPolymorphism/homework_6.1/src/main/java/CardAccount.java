public class CardAccount extends BankAccount {

    private final static int COMMISSION_PERCENTAGE = 1;

    @Override
    public void take(double amountToTake) {
        super.take(amountToTake, COMMISSION_PERCENTAGE);
    }

}
