package ro.itschool.homesphere.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.itschool.homesphere.entities.ServiceProvider;

import java.util.List;

public interface ServiceProviderRepository extends CrudRepository<ServiceProvider,Integer> {
    List<ServiceProvider> findByName(String name);
}
