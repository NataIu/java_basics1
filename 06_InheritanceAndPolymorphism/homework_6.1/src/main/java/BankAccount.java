public class BankAccount {

    private double amount = 0;

    public double getAmount() {
        return amount;
    }

    public boolean put(double amountToPut) {
        if (Double.compare(amountToPut,0) > 0) {
            amount = amount + amountToPut;
            return true;
        }
        return false;
    }

    public boolean take(double amountToTake) {
        return takeSum(amountToTake);
    }

    public boolean take(double amountToTake, int comissionPersentage) {
        double amountWithComission = amountToTake * (100+comissionPersentage)/100;
        return takeSum(amountWithComission);
    }

    public boolean takeSum(double amountToTake) {
        if ( Double.compare(amount, amountToTake) >= 0) {
            amount = amount - amountToTake;
            return true;
        }
        else {
            return false;
        }
    }


    public boolean send(BankAccount receiver, double amount) {

        double firstAmountBeforeOperations = getAmount();

        //снимем деньги с первого счета
        take(amount);
        if (Double.compare(firstAmountBeforeOperations,getAmount()) == 0) {
            //не получилось снять деньги с первого счета
            return false;
        }

        //положим деньги на второй счет
        boolean moneyPutSuccess = receiver.put(amount);
        if (!moneyPutSuccess) {
            //не получилось положить деньги на второй счет
            this.amount =  firstAmountBeforeOperations;
            return false;
        }

        return true;
    }

}
