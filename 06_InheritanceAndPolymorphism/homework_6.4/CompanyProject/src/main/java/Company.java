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

    public void hire(Employee employee, double salary) {
        employee.setHiringInformation(salary, this);
        employees.add(employee);
    }

    public void hireAll(List<Employee> employees, double salary) {

        for (Employee employee: employees) {
            hire(employee, salary);
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
        Collections.sort(employees,Comparator.comparing(Employee::getMonthSalary).reversed());
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
