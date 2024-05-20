package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BattalionGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BrigadeGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.LogisticCompanyDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.CompanyCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.LogisticCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Status;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.BrigadeCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.requests.SupplyRequestRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.BrigadeGroupService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.LogisticCompanyService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests.ResourcesRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BrigadeCommanderService {

    @Autowired
    private final BrigadeCommanderRepository brigadeCommanderRepository;
    @Autowired
    private final BrigadeGroupService brigadeGroupService;
    @Autowired
    private final LogisticCompanyService logisticCompanyService;
    @Autowired
    private final BattalionCommanderService battalionCommanderService;
    @Autowired
    private final ResourcesRequestService resourcesRequestService;
    @Autowired
    private final SupplyRequestRepository supplyRequestRepository;


    public BrigadeCommander getAuthenticatedBrigadeCommander() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String brigadeCommanderEmail = authentication.getName();
        return findBrigadeCommanderByEmail(brigadeCommanderEmail);
    }

    //TO DO: work with return statement
    public boolean createBrigade(BrigadeGroupDTO brigadeGroupDTO) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        brigadeGroupService.createBrigadeGroup(brigadeGroupDTO, brigadeCommander);
        BrigadeGroup brigadeGroup = brigadeGroupService.findBrigadeGroupByBrigadeCommander(brigadeCommander);
        brigadeCommander.setBrigadeGroupId(brigadeGroup);
        save(brigadeCommander);
        return true;
    }

    public boolean createLogisticCompany(LogisticCompanyDTO logisticCompanyDTO) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        return logisticCompanyService.createLogisticCompany(logisticCompanyDTO, brigadeCommander);
    }

    public boolean createBattalion(BattalionGroupDTO battalionGroupDTO) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        return battalionCommanderService.getBattalionGroupService().createBattalionGroup(battalionGroupDTO, brigadeCommander);
    }

    public boolean assignLogisticCompanyCommander(LogisticCommander logisticCompanyCommander){

        return true;
    }

    public boolean assignBattalionCommander(Integer battalionCommanderId, Integer battalionGroupId) {
        BattalionCommander battalionCommander = battalionCommanderService.findBattalionCommanderById(battalionCommanderId);
        BattalionGroup battalionGroup = battalionCommanderService.getBattalionGroupService().findBattalionGroupById(battalionGroupId);
        battalionGroup.setBattalionCommanderId(battalionCommander);
        battalionCommander.setBattalionGroup(battalionGroup);
        try {
            battalionCommanderService.save(battalionCommander);
            battalionCommanderService.getBattalionGroupService().save(battalionGroup);
        } catch (Exception e) {
            log.info("Something went wrong in assigning BattalionCommander.");
            return false;
        }

        return true;
    }
    @Transactional
    public void save(BrigadeCommander brigadeCommander) {
        brigadeCommanderRepository.save(brigadeCommander);
    }

    /*BrigadeCommander findBrigadeCommanderById(Integer id) {
        Optional<BrigadeCommander> tempBrigCom = brigadeCommanderRepository.findBrigadeCommanderByBrigadeCommanderId(id);
        if (tempBrigCom.isPresent()) {
            return tempBrigCom.get();
        } else {
            log.info("Error brigade commander with id" + id + " doesn't exist.");
        }
        return null;
    }*/

    public BrigadeCommander findBrigadeCommanderByEmail(String email) {
        Optional<BrigadeCommander> tempBrigCom = brigadeCommanderRepository.findBrigadeCommanderByEmail(email);
        if (tempBrigCom.isPresent()) {
            return tempBrigCom.get();
        } else {
            log.info("Error brigade commander with email" + email  + " doesn't exist.");
        }
        return null;
    }

    public boolean askForResources(ResourcesRequestDTO resourcesRequestDTO) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        ResourcesRequest resourcesRequest = ResourcesRequest.builder()
                .commanderId(brigadeCommander.getId())
                .militaryGroupId(brigadeCommander.getBrigadeGroupId().getId())
                .roleOfCommander(brigadeCommander.getRole())
                .ammo40mmGpCount(resourcesRequestDTO.getAmmo40mmGpCount())
                .ammo40mmRpgCount(resourcesRequestDTO.getAmmo40mmRpgCount())
                .ammo145KpvtCount(resourcesRequestDTO.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(resourcesRequestDTO.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(resourcesRequestDTO.getAmmo556x45ArCount())
                .ammo762PktCount(resourcesRequestDTO.getAmmo762PktCount())
                .ammo762x39AkCount(resourcesRequestDTO.getAmmo762x39AkCount())
                .offensiveGrenadesCount(resourcesRequestDTO.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(resourcesRequestDTO.getDefensiveGrenadesCount())
                .riflesCount(resourcesRequestDTO.getRiflesCount())
                .bodyArmorCount(resourcesRequestDTO.getBodyArmorCount())
                .helmetsCount(resourcesRequestDTO.getHelmetsCount())
                .apcCount(resourcesRequestDTO.getApcCount())
                .tankCount(resourcesRequestDTO.getTankCount())
                .build();
        SupplyRequest supplyRequest = SupplyRequest.builder()
                .dateOfRequest(LocalDate.now())
                .status(Status.NOT_PROCESSED)
                .build();
        try {
            resourcesRequestService.save(resourcesRequest);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        resourcesRequest = resourcesRequestService.findResourcesRequestByCommanderIdAndMilitaryGroupId(brigadeCommander.getId(), brigadeCommander.getBrigadeGroupId().getId());
        supplyRequest.setResourcesRequestId(resourcesRequest);
        try {
            supplyRequestRepository.save(supplyRequest);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }
}
