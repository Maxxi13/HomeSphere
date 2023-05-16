package ro.itschool.homesphere.service;

import org.springframework.stereotype.Service;
import ro.itschool.homesphere.dtos.UserDTO;
import ro.itschool.homesphere.entities.User;
import ro.itschool.homesphere.exceptions.AbsentResourceException;
import ro.itschool.homesphere.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());

        User savedUser = userRepository.save(user);
        return UserDTO.from(savedUser);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return UserDTO.from(users);
    }

    public UserDTO getUserById(int id) throws AbsentResourceException {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new AbsentResourceException("User does not exist.", id));
        return UserDTO.from(user);
    }

    public void deleteUser(int id) throws AbsentResourceException {
        if (!userRepository.existsById(id)) {
            throw new AbsentResourceException("User does not exist.", id);
        }
        userRepository.deleteById(id);
    }
}

