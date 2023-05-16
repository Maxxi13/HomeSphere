package ro.itschool.homesphere.dtos;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import ro.itschool.homesphere.entities.User;
import ro.itschool.homesphere.enums.UserRoles;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class UserDTO {
    private Integer id;
    @NotEmpty
    private String username;
    @NotEmpty
    @Size(min = 10, max = 50)
    private String password;
    @Email
    @NotNull
    private String email;
    @PastOrPresent
    private Timestamp createdAt;

    private UserRoles role;

    public static UserDTO from(User user) {
        return UserDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .password(user.getPassword())
            .email(user.getEmail())
            .createdAt(user.getCreatedAt())
            .role(user.getRole())
            .build();
    }

    public static List<UserDTO> from(List<User> users) {
        List<UserDTO> result = new ArrayList<>();

        for (User user : users) {
            result.add(UserDTO.from(user));
        }

        return result;
    }
}
