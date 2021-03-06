import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class Employee {

    private String name;
    private Integer salary;
    private Date workStart;

    public Employee(String name, Integer salary, Date workStart) {
        this.name = name;
        this.salary = salary;
        this.workStart = workStart;
    }

    public static List<Employee> loadStaffFromFile(String path) {
        List<Employee> staff = new ArrayList<>();
        try {
            String dateFormat = "dd.MM.yyyy";

            Files.readAllLines(Paths.get(path)).stream()
                    .filter(line -> StringUtils.countMatches(line, '\t') != 2)
                    .forEach(line -> System.out.println("Wrong line: " + line));

            staff = Files.readAllLines(Paths.get(path)).stream()
                    .map(line -> line.split("\t"))
                    .filter(line -> line.length == 3)
                    .map(line -> {
                        try {
                            return new Employee(line[0], Integer.parseInt(line[1]), (new SimpleDateFormat(dateFormat)).parse(line[2]));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getWorkStart() {
        return workStart;
    }

    public void setWorkStart(Date workStart) {
        this.workStart = workStart;
    }

    public String toString() {
        return name + " - " + salary + " - " +
                (new SimpleDateFormat("dd.MM.yyyy")).format(workStart);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(workStart, employee.workStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, workStart);
    }

}
