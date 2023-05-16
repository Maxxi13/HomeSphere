package ro.itschool.homesphere.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.itschool.homesphere.enums.UserRoles;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(name="created_at")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    @Enumerated(EnumType.STRING)
    private UserRoles role;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//        name = "roles_user",
//        joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
//        inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id")
//    )
//    private Set<RoleEntity> roles = new HashSet<>();
}
