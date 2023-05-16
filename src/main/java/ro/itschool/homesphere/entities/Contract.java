package ro.itschool.homesphere.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_and_time")
    private Timestamp dateAndTime;
    @Column(name = "number_of_hours")
    private int numberOfHours;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "service_provider_id")
    private ServiceProvider serviceProvider;
}
