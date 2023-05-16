package ro.itschool.homesphere.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.homesphere.dtos.ContractDTO;
import ro.itschool.homesphere.service.ContractService;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractResource {

    private final ContractService contractService;

    @Autowired
    public ContractResource(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public List<ContractDTO> getAllContracts() {
        return contractService.getAllContracts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDTO> getContractById(@PathVariable("id") int id) {
        ContractDTO contractDTO = contractService.getContractById(id);
        if (contractDTO != null) {
            return ResponseEntity.ok(contractDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ContractDTO> createContract(@RequestBody ContractDTO contractDTO) {
        ContractDTO createdContract = contractService.createContract(contractDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContract);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractDTO> updateContract(@PathVariable("id") int id, @RequestBody ContractDTO contractDTO) {
        ContractDTO updatedContract = contractService.updateContract(id, contractDTO);
        if (updatedContract != null) {
            return ResponseEntity.ok(updatedContract);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable("id") int id) {
        boolean deleted = contractService.deleteContract(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}




