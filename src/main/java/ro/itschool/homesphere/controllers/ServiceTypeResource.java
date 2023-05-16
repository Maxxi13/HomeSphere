package ro.itschool.homesphere.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.homesphere.dtos.ServiceTypeDTO;
import ro.itschool.homesphere.exceptions.AbsentResourceException;
import ro.itschool.homesphere.service.ServiceTypeService;

import java.util.List;

@RestController
@RequestMapping("/service-types")
public class ServiceTypeResource {
    private final ServiceTypeService serviceTypeService;

    public ServiceTypeResource(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceTypeDTO>> getAllServiceTypes() {
        List<ServiceTypeDTO> serviceTypes = serviceTypeService.getAllServiceTypes();
        return new ResponseEntity<>(serviceTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceTypeDTO> getServiceTypeById(@PathVariable("id") int id) {
        try {
            ServiceTypeDTO serviceType = serviceTypeService.getServiceTypeById(id);
            return new ResponseEntity<>(serviceType, HttpStatus.OK);
        } catch (AbsentResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ServiceTypeDTO> createServiceType(@RequestBody ServiceTypeDTO serviceTypeDTO) {
        ServiceTypeDTO createdServiceType = serviceTypeService.createServiceType(serviceTypeDTO);
        return new ResponseEntity<>(createdServiceType, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceType(@PathVariable("id") int id) {
        try {
            serviceTypeService.deleteServiceType(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (AbsentResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

