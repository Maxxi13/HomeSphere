package ro.itschool.homesphere.dtos;

import lombok.*;
import ro.itschool.homesphere.validators.Gmail;
import ro.itschool.homesphere.validators.NoDigits;
import ro.itschool.homesphere.entities.ServiceProvider;
import ro.itschool.homesphere.entities.ServiceType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceProviderDTO {

    private int id;
    @NotEmpty
    private String username;
    @NotEmpty
    @Size(min = 10, max = 50)
    private String password;
    @Email
    @NotEmpty
    @Gmail
    private String email;

    @PastOrPresent
    private Timestamp createdAt;
    @NoDigits(message = "Name cannot contain digits.")
    @NotEmpty
    private String name;
    @NotEmpty
    private String phone;

    private int serviceTypeId;

    private double hourlyPrice;
    private double rating;
    private int cityId;
    private Set<Integer> serviceTypeIds;

    public static ServiceProviderDTO from(ServiceProvider serviceProvider) {
        Set<Integer> serviceTypeIds = new LinkedHashSet<>();
        for (ServiceType serviceType : serviceProvider.getServiceTypes()) {
            serviceTypeIds.add(serviceType.getId());
        }
        return ServiceProviderDTO.builder()
            .id(serviceProvider.getId())
            .username(serviceProvider.getUsername())
            .password(serviceProvider.getPassword())
            .email(serviceProvider.getEmail())
            .createdAt(serviceProvider.getCreatedAt())
            .name(serviceProvider.getName())
            .phone(serviceProvider.getPhone())
            .serviceTypeId(serviceProvider.getServiceTypeId())
            .hourlyPrice(serviceProvider.getHourlyPrice())
            .rating(serviceProvider.getRating())
            .cityId(serviceProvider.getCity().getId())
            .serviceTypeIds(serviceTypeIds)
            .build();
    }

    public static List<ServiceProviderDTO> from(List<ServiceProvider> serviceProviders) {
        List<ServiceProviderDTO> result = new ArrayList<>();

        for (ServiceProvider serviceProvider : serviceProviders) {
            result.add(ServiceProviderDTO.from(serviceProvider));
        }

        return result;
    }
}
