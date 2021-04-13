package Entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "Subscriptions")
public class Subscription implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
