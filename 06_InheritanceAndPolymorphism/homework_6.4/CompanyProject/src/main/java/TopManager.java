public class TopManager implements Employee{

    private static final int BONUS_PERSENT = 150;
    private static final double MIN_COMPANY_INCOME = 10000000;

    private double fixSalary;
    private Company company;

    public TopManager(double fixSalary, Company company) {
        this.fixSalary = fixSalary;
        this.company = company;
    }

    @Override
    public double getMonthSalary() {
        return fixSalary+ (Double.compare(company.getIncome(),MIN_COMPANY_INCOME) > 0 ? fixSalary*BONUS_PERSENT/100: 0 );
    }

}
