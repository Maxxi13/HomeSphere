package ro.itschool.homesphere.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.itschool.homesphere.entities.Admin;

import java.util.List;

public interface AdminRepository extends CrudRepository<Admin,Integer> {
    List<Admin> findByName(String name);
}
