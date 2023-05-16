package ro.itschool.homesphere.dtos;

import lombok.*;
import ro.itschool.homesphere.validators.Gmail;
import ro.itschool.homesphere.validators.NoDigits;
import ro.itschool.homesphere.entities.Admin;

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
public class AdminDTO {
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

    public static AdminDTO from(Admin admin) {
        return AdminDTO.builder()
            .id(admin.getId())
            .username(admin.getUsername())
            .password(admin.getPassword())
            .email(admin.getEmail())
            .createdAt(admin.getCreatedAt())
            .name(admin.getName())
            .build();
    }

    public static List<AdminDTO> from(List<Admin> admins) {
        List<AdminDTO> result = new ArrayList<>();

        for (Admin admin : admins) {
            result.add(AdminDTO.from(admin));
        }

        return result;
    }
}

