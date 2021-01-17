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

    public void take(double amountToTake) {
        takeSum(amountToTake);
    }

    public void take(double amountToTake, int comissionPersentage) {
        double amountWithComission = amountToTake * (100+comissionPersentage)/100;
        takeSum(amountWithComission);
    }

    public void takeSum(double amountToTake) {
        if ( Double.compare(amount, amountToTake) >= 0) {
            amount = amount - amountToTake;
        }
    }


    public boolean send(BankAccount receiver, double amount) {

        boolean result = false;
        double firstAmountBeforeOperations = getAmount();
        double secondAmountBeforeOperations = 0;

        //снимем деньги с первого счета
        take(amount);
        if (Double.compare(firstAmountBeforeOperations,getAmount()) == 0) {
            //не получилось снять деньги с первого счета
            return result;
        }

        //положим деньги на второй счет
        secondAmountBeforeOperations = receiver.getAmount();
        receiver.put(amount);
        if (Double.compare(secondAmountBeforeOperations,receiver.getAmount()) == 0) {
            //не получилось положить деньги на второй счет
            amount =  firstAmountBeforeOperations;
            return result;
        }

        result = true;
        return result;
    }

}
