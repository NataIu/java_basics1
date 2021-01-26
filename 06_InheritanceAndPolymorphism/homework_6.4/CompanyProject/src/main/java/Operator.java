public class Operator implements Employee{

    private double fixSalary;

    @Override
    public double getMonthSalary() {
        return fixSalary;
    }

    @Override
    public void setHiringInformation(double salary, Company company){
        this.fixSalary = salary;
    }


}
