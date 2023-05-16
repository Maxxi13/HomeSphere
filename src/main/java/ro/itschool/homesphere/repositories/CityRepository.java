package ro.itschool.homesphere.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.itschool.homesphere.entities.City;

public interface CityRepository extends CrudRepository<City,Integer> {
}
