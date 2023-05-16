package ro.itschool.homesphere.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.itschool.homesphere.entities.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    List<Customer> findByName(String name);
}
