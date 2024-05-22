package nikita.ivakin.apzpzpi215ivakinnikitatask2.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.GivenResources;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.ResourcesType;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.GivenResourcesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class GivenResourcesService {

    private final GivenResourcesRepository givenResourcesRepository;

    public GivenResources getGivenResources(Integer commanderId, Integer militaryGroupId, Role roleOfCommander, Integer brigadeCommanderId, ResourcesType allocationOfResources) {
        Optional<GivenResources> tempGivenResources = givenResourcesRepository.findGivenResourcesByCommanderIdAndMilitaryGroupIdAndRoleOfCommanderAndBrigadeCommanderIdAndAllocationOfResources(
                commanderId, militaryGroupId, roleOfCommander, brigadeCommanderId, allocationOfResources
        );
        if (tempGivenResources.isPresent()) {
            return tempGivenResources.get();
        } else {
            log.info("Error given resources entity for this battle group doesn't exist.");
        }
        return null;
    }

    @Transactional
    public void save(GivenResources givenResources) {
        givenResourcesRepository.save(givenResources);
    }


}
