import java.util.*;

public class Company {

    private ArrayList<Employee> employees = new ArrayList();

    public int getCompanySize() {
        return employees.size();
    }

    public Employee getRandomEmployee() {
        int employeeIndex = (int) Math.round(Math.random()*(getCompanySize()-1));
        return employees.get(employeeIndex);
    }

    public void hire(EmployeePosition employeePosition, double salary) {

        Employee employee;
        if (employeePosition == EmployeePosition.TOP_MANAGER) {
            employee = new TopManager(salary, this);
        } else if (employeePosition == EmployeePosition.MANAGER) {
            employee = new Manager(salary);
        } else {
            //operator
            employee = new Operator(salary);
        }
        employees.add(employee);
    }

    public void hireAll(EmployeePosition employeePosition, double salary, int quantity) {
        for (int i = 0; i < quantity; i++) {
            hire(employeePosition, salary);
        }
    }

    public void fire(Employee employee) {
        if (employees.contains(employee) ) {
            employees.remove(employee);
        }
    }

    public double getIncome() {

        double income = 0;
        for (Employee employee:employees) {
            if (employee instanceof Manager) {
                income = income + ((Manager) employee).getProfitForCompany();
            }
        }
        return income;
    }

    public List<Employee> getTopSalaryStaff(int count) {

        int searchMinNumber = 0;
        int searchMaxNumber = Math.min(count,employees.size());
        List listSalaryStaff = getSomeSalaryStaff(searchMinNumber,searchMaxNumber);
        return listSalaryStaff;
    }

    public List<Employee> getLowestSalaryStaff(int count){

        int searchMinNumber = Math.max(0, employees.size() - count);
        int searchMaxNumber = employees.size();
        List listSalaryStaff = getSomeSalaryStaff(searchMinNumber,searchMaxNumber);
        return listSalaryStaff;

    }

    public List<Employee> getSomeSalaryStaff(int minNumber, int maxNumber){

        List listSalaryStaff = new ArrayList<Employee>();
        Collections.sort(employees,new EmployeeComparator());

        for (int i = minNumber; i < maxNumber; i++) {
            listSalaryStaff.add(employees.get(i));
        }

        return listSalaryStaff;

    }


    public static void printSalaryList(List<Employee> salaryList) {
        for (Employee employee : salaryList) {
            System.out.println(employee.getMonthSalary());
        }
    }

    public void printCompanyInformation() {
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(""+i+": "+getEmployeeInformation(employees.get(i)));
        }
    }

    private String getEmployeeInformation(Employee employee) {
        String text = "";

        if (employee instanceof Manager) {
            text = text + EmployeePosition.MANAGER +
                    ", month salary: " + employee.getMonthSalary()
                    + ", profit for company: " + ((Manager) employee).getProfitForCompany();
        } else if (employee instanceof TopManager) {
            text = text + EmployeePosition.TOP_MANAGER
                    + ", month salary: " + employee.getMonthSalary();
        } else {
            text = text + EmployeePosition.OPERATOR
                    + ", month salary: " + employee.getMonthSalary();
        }

        return text;
    }

}
