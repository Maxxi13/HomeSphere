package ro.itschool.homesphere.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.itschool.homesphere.dtos.CustomerDTO;
import ro.itschool.homesphere.entities.Customer;
import ro.itschool.homesphere.exceptions.AbsentResourceException;
import ro.itschool.homesphere.service.CustomerService;

import javax.validation.Valid;
import java.util.List;
@Validated
@RestController
@RequestMapping("/customers")
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") int id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        if (customerDTO != null) {
            return ResponseEntity.ok(customerDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(
        @PathVariable("id") int id,
        @RequestBody @Valid CustomerDTO customerDTO
    ) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(updatedCustomer);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> updatePartial(@PathVariable("id") int id, @RequestBody CustomerDTO customerDTO) {
        try {
            Customer updatedCustomer = customerService.updatePartial(id, customerDTO);

            CustomerDTO responseDto = CustomerDTO.from(updatedCustomer);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (AbsentResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") int id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<CustomerDTO> searchCustomersByName(@RequestParam("name") String name) {
        return customerService.findCustomersByName(name);
    }
}
