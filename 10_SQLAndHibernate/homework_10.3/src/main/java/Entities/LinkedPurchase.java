package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LinkedPurchaseList")
public class LinkedPurchase {

    @EmbeddedId
    private LinkedPurchaseId id;

    @Column(name = "student_id",insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id",insertable = false, updatable = false)
    private int courseId;

}
