public class Manager implements Employee {

    private static final int BONUS_PERSENT = 5;
    private static final double MIN_INCOME = 115000;
    private static final double MAX_INCOME = 140000;

    private double fixSalary;
    private double profitForCompany;

    public Manager(double fixSalary) {
        this.fixSalary = fixSalary;
        setManagersProfitForCompany();
    }

    @Override
    public double getMonthSalary() {
        return fixSalary +  ((double) Math.round(getProfitForCompany() * BONUS_PERSENT))/100;
    }

    private void setManagersProfitForCompany() {
        profitForCompany = ( (double) Math.round( 100*(MIN_INCOME + (MAX_INCOME - MIN_INCOME) * Math.random())))/100;
    }

    public double getProfitForCompany() {
        return profitForCompany;
    }
}
