package ro.itschool.homesphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.itschool.homesphere.dtos.CustomerDTO;
import ro.itschool.homesphere.entities.City;
import ro.itschool.homesphere.entities.Customer;
import ro.itschool.homesphere.enums.UserRoles;
import ro.itschool.homesphere.exceptions.AbsentResourceException;
import ro.itschool.homesphere.repositories.CityRepository;
import ro.itschool.homesphere.repositories.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CityRepository cityRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CityRepository cityRepository) {
        this.customerRepository = customerRepository;
        this.cityRepository = cityRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        return CustomerDTO.from(customers);
    }

    public CustomerDTO getCustomerById(int id) {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return CustomerDTO.from(customer);
        }
        return null; // or throw an exception
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(customerDTO.getPassword());
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setPhone(customerDTO.getPhone());
        customer.setAddress(customerDTO.getAddress());
        customer.setRole(UserRoles.CUSTOMER);
        City city = new City();
        city.setId(customerDTO.getCityId());
        customer.setCity(city);
        customer = customerRepository.save(customer);
        return CustomerDTO.from(customer);
    }

    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setUsername(customerDTO.getUsername());
            customer.setPassword(customerDTO.getPassword());
            customer.setEmail(customerDTO.getEmail());
            customer.setName(customerDTO.getName());
            customer.setPhone(customerDTO.getPhone());
            customer.setAddress(customerDTO.getAddress());
            City city = new City();
            city.setId(customerDTO.getCityId());
            customer.setCity(city);
            customer = customerRepository.save(customer);
            return CustomerDTO.from(customer);
        }
        return null; // or throw an exception
    }

    @Transactional
    public Customer updatePartial(int id, CustomerDTO customerDTO) throws AbsentResourceException {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (!optionalCustomer.isPresent()) {
            throw new AbsentResourceException("Customer does not exist.", id);
        }

        Customer customer = optionalCustomer.get();

        if (customerDTO.getName() != null) {
            customer.setName(customerDTO.getName());
        }

        if (customerDTO.getPhone() != null) {
            customer.setPhone(customerDTO.getPhone());
        }

        if (customerDTO.getAddress() != null) {
            customer.setAddress(customerDTO.getAddress());
        }

        if (customerDTO.getCityId() != 0) {
            City city = cityRepository.findById(customerDTO.getCityId())
                .orElseThrow(() -> new AbsentResourceException("City does not exist.", customerDTO.getCityId()));
            customer.setCity(city);
        }

        customerRepository.save(customer);
        return customer;
    }

    public boolean deleteCustomer(int id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            customerRepository.delete(optionalCustomer.get());
            return true;
        }
        return false; // or throw an exception
    }

    public List<CustomerDTO> findCustomersByName(String name) {
        List<Customer> customers = customerRepository.findByName(name);
        return CustomerDTO.from(customers);
    }
}
