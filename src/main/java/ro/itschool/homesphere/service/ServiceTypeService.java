package ro.itschool.homesphere.service;

import org.springframework.stereotype.Service;
import ro.itschool.homesphere.dtos.ServiceTypeDTO;
import ro.itschool.homesphere.entities.ServiceType;
import ro.itschool.homesphere.exceptions.AbsentResourceException;
import ro.itschool.homesphere.repositories.ServiceTypeRepository;

import java.util.List;

@Service
public class ServiceTypeService {
    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    public List<ServiceTypeDTO> getAllServiceTypes() {
        List<ServiceType> serviceTypes = (List<ServiceType>) serviceTypeRepository.findAll();
        return ServiceTypeDTO.from(serviceTypes);
    }

    public ServiceTypeDTO getServiceTypeById(int id) throws AbsentResourceException {
        ServiceType serviceType = serviceTypeRepository.findById(id)
            .orElseThrow(() -> new AbsentResourceException("Service type does not exist.", id));
        return ServiceTypeDTO.from(serviceType);
    }

    public ServiceTypeDTO createServiceType(ServiceTypeDTO serviceTypeDTO) {
        ServiceType serviceType = new ServiceType();
        serviceType.setName(serviceTypeDTO.getName());

        ServiceType savedServiceType = serviceTypeRepository.save(serviceType);
        return ServiceTypeDTO.from(savedServiceType);
    }

    public void deleteServiceType(int id) throws AbsentResourceException {
        if (!serviceTypeRepository.existsById(id)) {
            throw new AbsentResourceException("Service type does not exist.", id);
        }
        serviceTypeRepository.deleteById(id);
    }
}

