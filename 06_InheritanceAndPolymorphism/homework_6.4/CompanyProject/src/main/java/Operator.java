public class Operator implements Employee{

    private double fixSalary;

    public Operator(double fixSalary) {
        this.fixSalary = fixSalary;
    }

    @Override
    public double getMonthSalary() {
        return fixSalary;
    }

}
