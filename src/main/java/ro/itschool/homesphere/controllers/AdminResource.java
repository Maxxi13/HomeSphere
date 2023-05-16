package ro.itschool.homesphere.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.itschool.homesphere.dtos.AdminDTO;
import ro.itschool.homesphere.service.AdminService;

import javax.validation.Valid;
import java.util.List;
@Validated
@RestController
@RequestMapping("/admins")
public class AdminResource {
    private final AdminService adminService;

    public AdminResource(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<AdminDTO> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable int id) {
        AdminDTO adminDTO = adminService.getAdminById(id);
        if (adminDTO != null) {
            return ResponseEntity.ok(adminDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody @Valid AdminDTO adminDTO) {
        AdminDTO createdAdmin = adminService.createAdmin(adminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable int id, @RequestBody @Valid AdminDTO adminDTO) {
        AdminDTO updatedAdmin = adminService.updateAdmin(id, adminDTO);
        if (updatedAdmin != null) {
            return ResponseEntity.ok(updatedAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable int id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}

