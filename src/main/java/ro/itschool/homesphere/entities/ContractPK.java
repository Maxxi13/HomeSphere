package ro.itschool.homesphere.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ContractPK implements Serializable {
    @Column(name = "id_customer")
    private int idCustomer;
    @Column(name = "id_service_provider")
    private int idServiceProvider;
}
