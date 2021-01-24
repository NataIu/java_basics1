import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Создайте и наймите в компанию: 180 операторов Operator, 80 менеджеров по продажам Manager, 10 топ-менеджеров TopManager.
        Company company = new Company();
        company.hireAll(EmployeePosition.OPERATOR, 10000.0, 20);
        company.hireAll(EmployeePosition.OPERATOR, 12000.0, 160);
        company.hireAll(EmployeePosition.MANAGER, 50000.0, 80);
        company.hireAll(EmployeePosition.TOP_MANAGER, 100000.0, 10);

//        //контроль
//        System.out.println("Company income: " + company.getIncome());
//        System.out.println("Employees list: ");
//        company.printCompanyInformation();

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

        System.out.println("AFTER FIRING");

        //Распечатайте список из 10–15 самых высоких зарплат в компании.
        System.out.println("10 top salary: ");
        Company.printSalaryList(company.getTopSalaryStaff(10));

        //Распечатайте список из 30 самых низких зарплат в компании.
        System.out.println("30 low salary: ");
        Company.printSalaryList(company.getLowestSalaryStaff(30));


    }

//    public void smallTest() {
//
//        Company company = new Company();
//
//        company.hire(EmployeePosition.OPERATOR, 10000);
//        company.hire(EmployeePosition.MANAGER, 60000);
//        company.hire(EmployeePosition.TOP_MANAGER, 100000);
//        company.hire(EmployeePosition.OPERATOR, 10000);
//        company.hire(EmployeePosition.MANAGER, 60000);
//        company.hire(EmployeePosition.TOP_MANAGER, 100000);
//        System.out.println("Company income: " + company.getIncome());
//        System.out.println("--");
//        company.printCompanyInformation();
//        System.out.println("--");
//        Company.printSalaryList(company.getTopSalaryStaff(1));
//        System.out.println("--");
//        Company.printSalaryList(company.getTopSalaryStaff(5));
//        System.out.println("--");
//        Company.printSalaryList(company.getLowestSalaryStaff(6));
//        System.out.println("--");
//        Company.printSalaryList(company.getLowestSalaryStaff(2));
//
//    }

}
