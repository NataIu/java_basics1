
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {

        Date startOfYear = Date.from(LocalDate.of(year, 1, 1).atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant());

        Date startOfNextYear = Date.from(LocalDate.of(year+1, 1, 1).atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant());

        Optional optional = staff.stream().filter(s -> s.getWorkStart().before(startOfNextYear))
                .filter(s -> s.getWorkStart().after(startOfYear) || s.getWorkStart().equals(startOfYear))
                .max(Comparator.comparingInt(Employee::getSalary));
        if (optional.isPresent()) {
            return (Employee) optional.get();
        }
        return null;
    }
}