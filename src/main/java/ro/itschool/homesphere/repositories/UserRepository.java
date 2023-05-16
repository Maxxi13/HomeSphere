package ro.itschool.homesphere.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.itschool.homesphere.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {


    Optional<User> findByEmail(String email);
    List<User> findByUsername(String username);// pt Basic auth trebuie Optional
}
