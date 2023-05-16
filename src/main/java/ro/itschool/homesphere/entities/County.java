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
@Table(name = "counties")
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
//    @OneToMany(mappedBy = "county", cascade = CascadeType.ALL)
//    private List<City> cities = new ArrayList<>();
}
