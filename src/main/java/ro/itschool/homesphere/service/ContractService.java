package ro.itschool.homesphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.homesphere.dtos.ContractDTO;
import ro.itschool.homesphere.entities.Contract;
import ro.itschool.homesphere.entities.Customer;
import ro.itschool.homesphere.entities.ServiceProvider;
import ro.itschool.homesphere.repositories.ContractRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<ContractDTO> getAllContracts() {
        List<Contract> contracts = (List<Contract>) contractRepository.findAll();
        return ContractDTO.from(contracts);
    }

    public ContractDTO getContractById(int id) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();
            return ContractDTO.from(contract);
        }
        return null; // or throw an exception
    }

    public ContractDTO createContract(ContractDTO contractDTO) {
        Contract contract = new Contract();
        contract.setId(contractDTO.getId());
        contract.setDateAndTime(contractDTO.getDateAndTime());
        contract.setNumberOfHours(contractDTO.getNumberOfHours());
        Customer customer = new Customer();
        customer.setId(contractDTO.getCustomer());
        contract.setCustomer(customer);
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setId(contractDTO.getServiceProvider());
        contract.setServiceProvider(serviceProvider);
        contract = contractRepository.save(contract);
        return ContractDTO.from(contract);
    }

    public ContractDTO updateContract(int id, ContractDTO contractDTO) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();
            contract.setDateAndTime(contractDTO.getDateAndTime());
            contract.setNumberOfHours(contractDTO.getNumberOfHours());
            Customer customer = new Customer();
            customer.setId(contractDTO.getCustomer());
            contract.setCustomer(customer);
            ServiceProvider serviceProvider = new ServiceProvider();
            serviceProvider.setId(contractDTO.getServiceProvider());
            contract.setServiceProvider(serviceProvider);
            contract = contractRepository.save(contract);
            return ContractDTO.from(contract);
        }
        return null; // or throw an exception
    }

    public boolean deleteContract(int id) {
        Optional<Contract> optionalContract = contractRepository.findById(id);
        if (optionalContract.isPresent()) {
            contractRepository.delete(optionalContract.get());
            return true;
        }
        return false; // or throw an exception
    }
}
