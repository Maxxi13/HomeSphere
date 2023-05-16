package ro.itschool.homesphere.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ro.itschool.homesphere.entities.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends CrudRepository<Contract,Integer>{

    Optional<Contract> findById(int id);

    @Query("SELECT new ro.itschool.homesphere.repositories.ContractsCount(id, count(*)) from Contract GROUP BY id")
    List<ContractsCount> countContracts();
}
