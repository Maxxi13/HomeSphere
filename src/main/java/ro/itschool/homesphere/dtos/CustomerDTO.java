package ro.itschool.homesphere.dtos;

import lombok.*;
import ro.itschool.homesphere.validators.Gmail;
import ro.itschool.homesphere.validators.NoDigits;
import ro.itschool.homesphere.entities.Customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

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
    @NotEmpty
    private String address;
    private int cityId;

    public static CustomerDTO from(Customer customer) {
        return CustomerDTO.builder()
            .id(customer.getId())
            .username(customer.getUsername())
            .password(customer.getPassword())
            .email(customer.getEmail())
            .createdAt(customer.getCreatedAt())
            .name(customer.getName())
            .phone(customer.getPhone())
            .address(customer.getAddress())
            .cityId(customer.getCity().getId())
            .build();
    }

    public static List<CustomerDTO> from(List<Customer> customers) {
        List<CustomerDTO> result = new ArrayList<>();

        for (Customer customer : customers) {
            result.add(CustomerDTO.from(customer));
        }

        return result;
    }
}