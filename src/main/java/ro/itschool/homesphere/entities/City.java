package ro.itschool.homesphere.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "county_id")
    private County county;

//    @OneToMany(mappedBy = "city")
//    private List<Customer> customers = new ArrayList<>();
//    @OneToMany(mappedBy = "city")
//    private List<ServiceProvider> serviceProviders = new ArrayList<>();

}
