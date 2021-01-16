public class IndividualBusinessman extends Client {

    private final static double COMMISSION_FOR_LESS_THAN_1000 = 1;
    private final static double COMMISSION_FOR_BIGGER_OR_EQUALS_1000 = 0.5;

    @Override
    public void put(double amountToPut) {
        double currentPersentage = COMMISSION_FOR_BIGGER_OR_EQUALS_1000;
        if (Double.compare(amountToPut, 1000) < 0) {
            currentPersentage = COMMISSION_FOR_LESS_THAN_1000;
        }
        double amountWithComission = amountToPut * (100 - currentPersentage) / 100;
        super.put(amountWithComission);
    }

}
