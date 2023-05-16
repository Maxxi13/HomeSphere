package ro.itschool.homesphere.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.itschool.homesphere.entities.ServiceType;

public interface ServiceTypeRepository extends CrudRepository<ServiceType,Integer> {
}
