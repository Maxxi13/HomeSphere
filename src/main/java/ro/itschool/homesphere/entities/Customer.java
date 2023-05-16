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
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends User{
    private String name;
    private String phone;

    private String address;
    @ManyToOne
    @JoinColumn(name= "city_id")
    private City city;
}
