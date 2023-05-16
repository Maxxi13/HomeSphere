package ro.itschool.homesphere.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.itschool.homesphere.entities.ServiceType;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTypeDTO {
    private int id;
    private String name;

    public static ServiceTypeDTO from(ServiceType serviceType) {
        return new ServiceTypeDTO(serviceType.getId(), serviceType.getName());
    }

    public static List<ServiceTypeDTO> from(List<ServiceType> serviceTypes) {
        return serviceTypes.stream()
            .map(ServiceTypeDTO::from)
            .collect(Collectors.toList());
    }
}

