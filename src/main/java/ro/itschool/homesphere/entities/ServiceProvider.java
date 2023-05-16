package ro.itschool.homesphere.entities;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_providers")
@PrimaryKeyJoinColumn(name = "id")
public class ServiceProvider extends User{
    private String name;
    private String phone;
    @Column(name = "service_type_id")
    private int serviceTypeId;

    @Column(name = "hourly_price")
    private double hourlyPrice;
    private double rating;

    @ManyToOne
    @JoinColumn(name= "city_id")
    private City city;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "service_providers_type",
        joinColumns = {@JoinColumn(name = "id_provider", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "id_type", referencedColumnName = "id")}
    )
    private Set<ServiceType> serviceTypes = new LinkedHashSet<>();
}
