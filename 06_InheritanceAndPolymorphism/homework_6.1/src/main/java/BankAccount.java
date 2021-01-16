public class BankAccount {

    private double amount = 0;

    public double getAmount() {
        return amount;
    }

    public void put(double amountToPut) {
        if (Double.compare(amountToPut,0) > 0) {
            amount = amount + amountToPut;
        }
    }

    public void take(double amountToTake) {
        if ( Double.compare(amount, amountToTake) > 0) {
            amount = amount - amountToTake;
        }
    }

}
