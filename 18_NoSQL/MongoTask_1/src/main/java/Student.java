import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
public class Student {

    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1)
    private int age;

    @CsvBindByPosition(position = 2)
    private String courses;

    private List<String> coursesList;

    public void setCourses(String courses) {
        this.courses = courses;
        coursesList = Arrays.asList(courses.split(","));
    }
}
