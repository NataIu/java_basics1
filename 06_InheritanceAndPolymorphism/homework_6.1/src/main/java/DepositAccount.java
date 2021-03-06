import java.time.LocalDate;

public class DepositAccount extends BankAccount {

    private LocalDate lastIncome;

    @Override
    public boolean put(double amountToPut) {

        boolean result = super.put(amountToPut);
        if (result) {
            lastIncome = LocalDate.now();
        }

        return result;

    }

    @Override
    public boolean take(double amountToTake) {
        if (lastIncome.plusMonths(1).isBefore(LocalDate.now())) {
            return super.take(amountToTake);
        }
        else {
            return false;
        }
    }

}
