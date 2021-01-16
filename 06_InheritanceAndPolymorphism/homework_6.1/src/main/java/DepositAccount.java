import java.time.LocalDate;

public class DepositAccount extends BankAccount {

    private LocalDate lastIncome;

    @Override
    public void put(double amountToPut) {

        double amountBeforePutting = getAmount();
        double amountAfterPutting = 0;

        super.put(amountToPut);
        amountAfterPutting = getAmount();

        if (Double.compare(amountBeforePutting,amountAfterPutting) < 0) {
            lastIncome = LocalDate.now();
        }

    }

    @Override
    public void take(double amountToTake) {
        if (lastIncome.plusMonths(1).isBefore(LocalDate.now())) {
            super.take(amountToTake);
        }
    }

}
