package ro.itschool.homesphere.dtos;

import lombok.*;
import ro.itschool.homesphere.entities.Contract;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {

    private int id;
    private Timestamp dateAndTime;
    private int numberOfHours;
    private int customer;
    private int serviceProvider;

    public static ContractDTO from(Contract contract) {
        return ContractDTO.builder()
            .id(contract.getId())
            .dateAndTime(contract.getDateAndTime())
            .numberOfHours(contract.getNumberOfHours())
            .customer(contract.getCustomer().getId())
            .serviceProvider(contract.getServiceProvider().getId())
            .build();
    }

    public static List<ContractDTO> from(List<Contract> contracts) {
        List<ContractDTO> result = new ArrayList<>();

        for (Contract contract : contracts) {
            result.add(ContractDTO.from(contract));
        }

        return result;
    }
}

