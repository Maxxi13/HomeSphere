package ro.itschool.homesphere.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.itschool.homesphere.dtos.ServiceProviderDTO;
import ro.itschool.homesphere.entities.City;
import ro.itschool.homesphere.entities.ServiceProvider;
import ro.itschool.homesphere.entities.ServiceType;
import ro.itschool.homesphere.enums.UserRoles;
import ro.itschool.homesphere.exceptions.AbsentResourceException;
import ro.itschool.homesphere.repositories.CityRepository;
import ro.itschool.homesphere.repositories.ServiceProviderRepository;
import ro.itschool.homesphere.repositories.ServiceTypeRepository;

import java.util.*;

@Service
public class ServiceProviderService {

    private final ServiceProviderRepository repository;
    private final CityRepository cityRepository;
    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceProviderService(ServiceProviderRepository repository, CityRepository cityRepository,
                                  ServiceTypeRepository serviceTypeRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
        this.serviceTypeRepository = serviceTypeRepository;
    }

    public ServiceProviderDTO createServiceProvider(ServiceProviderDTO dto) {
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setUsername(dto.getUsername());
        serviceProvider.setPassword(dto.getPassword());
        serviceProvider.setEmail(dto.getEmail());
        serviceProvider.setCreatedAt(dto.getCreatedAt());
        serviceProvider.setName(dto.getName());
        serviceProvider.setPhone(dto.getPhone());
        serviceProvider.setServiceTypeId(dto.getServiceTypeId());
        serviceProvider.setHourlyPrice(dto.getHourlyPrice());
        serviceProvider.setRating(dto.getRating());
        serviceProvider.setRole(UserRoles.SERVICEPROVIDER);
        serviceProvider.setCity(cityRepository.findById(dto.getCityId())
            .orElseThrow(() -> new NoSuchElementException("City not found with id: " + dto.getCityId())));
        Set<ServiceType> serviceTypes = new HashSet<>();
        for (Integer typeId : dto.getServiceTypeIds()) {
            serviceTypes.add(serviceTypeRepository.findById(typeId)
                .orElseThrow(() -> new NoSuchElementException("Service type not found with id: " + typeId)));
        }
        serviceProvider.setServiceTypes(serviceTypes);
        serviceProvider = repository.save(serviceProvider);
        return ServiceProviderDTO.from(serviceProvider);
    }

    public ServiceProviderDTO getServiceProviderById(int id) {
        ServiceProvider serviceProvider = repository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Service provider not found with id: " + id));
        return ServiceProviderDTO.from(serviceProvider);
    }

    public List<ServiceProviderDTO> getAllServiceProviders() {
        List<ServiceProvider> serviceProviders = (List<ServiceProvider>) repository.findAll();
        return ServiceProviderDTO.from(serviceProviders);
    }

    public List<ServiceProviderDTO> getServiceProvidersByName(String name) {
        List<ServiceProvider> serviceProviders = repository.findByName(name);
        return ServiceProviderDTO.from(serviceProviders);
    }

    public ServiceProviderDTO updateServiceProvider(int id, ServiceProviderDTO dto) {
        ServiceProvider serviceProvider = repository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Service provider not found with id: " + id));
        serviceProvider.setUsername(dto.getUsername());
        serviceProvider.setPassword(dto.getPassword());
        serviceProvider.setEmail(dto.getEmail());
        serviceProvider.setCreatedAt(dto.getCreatedAt());
        serviceProvider.setName(dto.getName());
        serviceProvider.setPhone(dto.getPhone());
        serviceProvider.setServiceTypeId(dto.getServiceTypeId());
        serviceProvider.setHourlyPrice(dto.getHourlyPrice());
        serviceProvider.setRating(dto.getRating());
        serviceProvider.setCity(cityRepository.findById(dto.getCityId())
            .orElseThrow(() -> new NoSuchElementException("City not found with id: " + dto.getCityId())));
        Set<ServiceType> serviceTypes = new HashSet<>();
        for (Integer typeId : dto.getServiceTypeIds()) {
            serviceTypes.add(serviceTypeRepository.findById(typeId)
                .orElseThrow(() -> new NoSuchElementException("Service type not found with id: " + typeId)));
        }
        serviceProvider.setServiceTypes(serviceTypes);
        serviceProvider = repository.save(serviceProvider);
        return ServiceProviderDTO.from(serviceProvider);
    }

    @Transactional
    public ServiceProvider updatePartial(int id, ServiceProviderDTO serviceProviderDTO) throws AbsentResourceException {
        Optional<ServiceProvider> optionalServiceProvider = repository.findById(id);

        if (!optionalServiceProvider.isPresent()) {
            throw new AbsentResourceException("Service provider does not exist.", id);
        }

        ServiceProvider serviceProvider = optionalServiceProvider.get();

        if (serviceProviderDTO.getName() != null) {
            serviceProvider.setName(serviceProviderDTO.getName());
        }

        if (serviceProviderDTO.getPhone() != null) {
            serviceProvider.setPhone(serviceProviderDTO.getPhone());
        }

        if (serviceProviderDTO.getServiceTypeId() != 0) {
            serviceProvider.setServiceTypeId(serviceProviderDTO.getServiceTypeId());
        }

        if (serviceProviderDTO.getHourlyPrice() != 0.0) {
            serviceProvider.setHourlyPrice(serviceProviderDTO.getHourlyPrice());
        }

        if (serviceProviderDTO.getRating() != 0.0) {
            serviceProvider.setRating(serviceProviderDTO.getRating());
        }

        if (serviceProviderDTO.getCityId() != 0) {
            City city = cityRepository.findById(serviceProviderDTO.getCityId())
                .orElseThrow(() -> new AbsentResourceException("City does not exist.", serviceProviderDTO.getCityId()));
            serviceProvider.setCity(city);
        }

        // Update the service types
        if (serviceProviderDTO.getServiceTypeIds() != null) {
            Set<ServiceType> serviceTypes = new HashSet<>();
            for (Integer typeId : serviceProviderDTO.getServiceTypeIds()) {
                ServiceType serviceType = serviceTypeRepository.findById(typeId)
                    .orElseThrow(() -> new AbsentResourceException("Service type does not exist.", typeId));
                serviceTypes.add(serviceType);
            }
            serviceProvider.setServiceTypes(serviceTypes);
        }

        return repository.save(serviceProvider);
    }

    public boolean deleteServiceProvider(int id) {
        repository.deleteById(id);
        return false;
    }


    public List<ServiceProviderDTO> findServiceProvidersByName(String name) {
        List<ServiceProvider> serviceProviders = repository.findByName(name);
        return ServiceProviderDTO.from(serviceProviders);
    }
}
