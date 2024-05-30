package nikita.ivakin.apzpzpi215ivakinnikitatask2.service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.SupplyCarDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyCar;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.LogisticCompany;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.requests.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.SupplyCarNotFoundException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.SupplyCarRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.LogisticCompanyService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests.SupplyRequestService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SupplyCarService {

    private final SupplyCarRepository supplyCarRepository;
    private final SupplyRequestService supplyRequestService;
    private final LogisticCompanyService logisticCompanyService;

    public SupplyCar mapDtoToEntity(SupplyCarDTO supplyCarDTO) {
        SupplyRequest supplyRequest = supplyRequestService.getSupplyRequestById(supplyCarDTO.getSupplyRequestId());
        LogisticCompany logisticCompany = logisticCompanyService.findLogisticCompanyById(supplyCarDTO.getLogisticCompanyId());
        return SupplyCar.builder()
                .id(supplyCarDTO.getId())
                .tier(supplyCarDTO.getTier())
                .supplyRequest(supplyRequest)
                .build();
    }

    public SupplyCar findSupplyCarById(Integer id) {
        Optional<SupplyCar> tempSupplyCar = supplyCarRepository.findSupplyCarById(id);
        if (tempSupplyCar.isPresent()) {
            return tempSupplyCar.get();
        } else {
            throw new SupplyCarNotFoundException("There aren't any cars with id " +id);
        }
    }

    @Transactional
    public void save(SupplyCar supplyCar) {
        supplyCarRepository.save(supplyCar);
    }



}
