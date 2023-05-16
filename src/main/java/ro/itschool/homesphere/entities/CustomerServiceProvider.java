package ro.itschool.homesphere.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Getter
@Setter
@Entity
@Table(name = "customers_service_providers")
public class CustomerServiceProvider {
    @EmbeddedId
    private CustomerServiceProviderPK id;
    private int rating;
    @ToString.Exclude
    @ManyToOne
    @MapsId("idCustomer")
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ToString.Exclude
    @ManyToOne
    @MapsId("idServiceProvider")
    @JoinColumn(name = "service_provider_id")
    private ServiceProvider serviceProvider;

}
