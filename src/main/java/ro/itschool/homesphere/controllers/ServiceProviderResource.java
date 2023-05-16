package ro.itschool.homesphere.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.itschool.homesphere.dtos.ServiceProviderDTO;
import ro.itschool.homesphere.entities.ServiceProvider;
import ro.itschool.homesphere.exceptions.AbsentResourceException;
import ro.itschool.homesphere.service.ServiceProviderService;

import javax.validation.Valid;
import java.util.List;
@Validated
@RestController
@RequestMapping("/service-providers")
public class ServiceProviderResource {

    private final ServiceProviderService serviceProviderService;

    public ServiceProviderResource(ServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }

    @GetMapping
    public List<ServiceProviderDTO> getAllServiceProviders() {
        return serviceProviderService.getAllServiceProviders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceProviderDTO> getServiceProviderById(@PathVariable("id") int id) {
        ServiceProviderDTO serviceProviderDTO = serviceProviderService.getServiceProviderById(id);
        if (serviceProviderDTO != null) {
            return ResponseEntity.ok(serviceProviderDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ServiceProviderDTO> createServiceProvider(
        @RequestBody @Valid ServiceProviderDTO serviceProviderDTO
    ) {
        ServiceProviderDTO createdServiceProvider = serviceProviderService.createServiceProvider(serviceProviderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdServiceProvider);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceProviderDTO> updateServiceProvider(
        @PathVariable("id") int id,
        @RequestBody @Valid ServiceProviderDTO serviceProviderDTO
    ) {
        ServiceProviderDTO updatedServiceProvider = serviceProviderService.updateServiceProvider(id, serviceProviderDTO);
        if (updatedServiceProvider != null) {
            return ResponseEntity.ok(updatedServiceProvider);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServiceProviderDTO> updatePartial(@PathVariable("id") int id, @RequestBody ServiceProviderDTO serviceProviderDTO) {
        try {
            ServiceProvider updatedServiceProvider = serviceProviderService.updatePartial(id, serviceProviderDTO);

            ServiceProviderDTO responseDto = ServiceProviderDTO.from(updatedServiceProvider);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (AbsentResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceProvider(@PathVariable("id") int id) {
        boolean deleted = serviceProviderService.deleteServiceProvider(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<ServiceProviderDTO> searchServiceProvidersByName(@RequestParam("name") String name) {
        return serviceProviderService.findServiceProvidersByName(name);
    }
}
