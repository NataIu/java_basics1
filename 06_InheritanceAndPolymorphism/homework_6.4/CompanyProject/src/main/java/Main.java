import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        //Создайте и наймите в компанию:  10 топ-менеджеров TopManager.
        Company company = new Company();

        List<Employee> people= new ArrayList<Employee>();

        //наймите в компанию: 180 операторов Operator
        people = createPeople(EmployeePosition.OPERATOR, 1);
        company.hire(people.get(0), 10000.0);
        people = createPeople(EmployeePosition.OPERATOR, 19);
        company.hireAll(people, 10000.0);
        people = createPeople(EmployeePosition.OPERATOR, 160);
        company.hireAll(people, 12000.0);

        //наймите в компанию: 80 менеджеров по продажам Manager
        people = createPeople(EmployeePosition.MANAGER, 1);
        company.hire(people.get(0), 50000.0);
        people = createPeople(EmployeePosition.MANAGER, 79);
        company.hireAll(people, 60000.0);

        //наймите в компанию:  10 топ-менеджеров TopManager
        people = createPeople(EmployeePosition.TOP_MANAGER, 1);
        company.hire(people.get(0), 100000.0);
        people = createPeople(EmployeePosition.TOP_MANAGER, 9);
        company.hireAll(people, 110000.0);


//        //контроль
//        System.out.println("Company income: " + company.getIncome());
//        System.out.println("Employees list: ");
//        company.printCompanyInformation();
//        System.out.println("Employees list end.");

        //Распечатайте список из 10–15 самых высоких зарплат в компании.
        System.out.println("10 top salary: ");
        Company.printSalaryList(company.getTopSalaryStaff(10));


        //Распечатайте список из 30 самых низких зарплат в компании.
        System.out.println("30 low salary: ");
        Company.printSalaryList(company.getLowestSalaryStaff(30));

        //Увольте 50% сотрудников.
        int companySize = company.getCompanySize();
        for (int i = 0; i < companySize/2; i++) {
            Employee employee = company.getRandomEmployee();
            company.fire(employee);
        }

//        //контроль
//        System.out.println("Company income: " + company.getIncome());
//        System.out.println("Employees list: ");
//        company.printCompanyInformation();
//        System.out.println("Employees list end.");

        System.out.println("AFTER FIRING");

        //Распечатайте список из 10–15 самых высоких зарплат в компании.
        System.out.println("10 top salary: ");
        Company.printSalaryList(company.getTopSalaryStaff(10));

        //Распечатайте список из 30 самых низких зарплат в компании.
        System.out.println("30 low salary: ");
        Company.printSalaryList(company.getLowestSalaryStaff(30));

    }

    private static List<Employee> createPeople(EmployeePosition employeePosition, int quantity) {

        List<Employee> people = new ArrayList<Employee>();
        for (int i = 0; i < quantity; i++) {
            if (employeePosition == EmployeePosition.TOP_MANAGER) {
                people.add(new TopManager());
            } else if (employeePosition == EmployeePosition.MANAGER) {
                people.add(new Manager());
            } else {
                //operator
                people.add(new Operator());
            }

        }
        return people;
    }

}
