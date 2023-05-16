package ro.itschool.homesphere.service;

import org.springframework.stereotype.Service;
import ro.itschool.homesphere.dtos.AdminDTO;
import ro.itschool.homesphere.entities.Admin;
import ro.itschool.homesphere.enums.UserRoles;
import ro.itschool.homesphere.repositories.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<AdminDTO> getAllAdmins() {
        Iterable<Admin> admins = adminRepository.findAll();
        return AdminDTO.from((List<Admin>) admins);
    }

    public AdminDTO getAdminById(int id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        return optionalAdmin.map(AdminDTO::from).orElse(null);
    }

    public AdminDTO createAdmin(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setUsername(adminDTO.getUsername());
        admin.setPassword(adminDTO.getPassword());
        admin.setEmail(adminDTO.getEmail());
        admin.setRole(UserRoles.ADMIN);
        admin.setName(adminDTO.getName());

        Admin savedAdmin = adminRepository.save(admin);
        return AdminDTO.from(savedAdmin);
    }

    public AdminDTO updateAdmin(int id, AdminDTO adminDTO) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            admin.setUsername(adminDTO.getUsername());
            admin.setPassword(adminDTO.getPassword());
            admin.setEmail(adminDTO.getEmail());
            admin.setName(adminDTO.getName());

            Admin updatedAdmin = adminRepository.save(admin);
            return AdminDTO.from(updatedAdmin);
        }
        return null;
    }

    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }
}

