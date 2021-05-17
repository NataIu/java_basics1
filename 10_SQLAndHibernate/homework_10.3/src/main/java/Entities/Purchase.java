package Entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "PurchaseList")
public class Purchase implements Serializable {

    @Id
    @Column(name = "student_name")
    private String studentName;

    @Id
    @Column(name = "course_name")
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "course_name", referencedColumnName = "name")
    })
    private  Course course;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "student_name", referencedColumnName = "name")
    })
    private  Student student;
}
