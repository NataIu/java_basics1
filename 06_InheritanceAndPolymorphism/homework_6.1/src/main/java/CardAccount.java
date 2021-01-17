public class CardAccount extends BankAccount {

    private final static int COMMISSION_PERCENTAGE = 1;

    @Override
    public boolean take(double amountToTake) {
        return super.take(amountToTake, COMMISSION_PERCENTAGE);
    }

}
