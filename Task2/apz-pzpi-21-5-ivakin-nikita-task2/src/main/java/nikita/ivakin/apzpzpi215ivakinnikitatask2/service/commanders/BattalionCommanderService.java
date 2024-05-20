package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.CompanyGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.CompanyCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.CompanyGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Status;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.BattalionCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.BattalionGroupService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.CompanyGroupService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests.ResourcesRequestService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests.SupplyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BattalionCommanderService {

    @Autowired
    private final BattalionCommanderRepository battalionCommanderRepository;
    @Autowired
    private final BattalionGroupService battalionGroupService;
    @Autowired
    private final CompanyCommanderService companyCommanderService;
    @Autowired
    private final CompanyGroupService companyGroupService;
    @Autowired
    private final ResourcesRequestService resourcesRequestService;
    @Autowired
    private final SupplyRequestService supplyRequestService;



    public BattalionCommander getAuthenticatedBattalionCommander() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String battalionCommanderEmail = authentication.getName();
        return findBattalionCommanderByEmail(battalionCommanderEmail);
    }

    public boolean createCompany(CompanyGroupDTO companyGroupDTO) {
        BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
        return companyGroupService.createCompanyGroup(companyGroupDTO, battalionCommander);
    }

    public boolean assignCompanyCommander(Integer companyCommanderId, Integer companyGroupId){
        CompanyCommander companyCommander = companyCommanderService.findCompanyCommanderById(companyCommanderId);
        CompanyGroup companyGroup = companyGroupService.findCompanyGroupById(companyGroupId);
        companyGroup.setCompanyCommanderId(companyCommander);
        companyCommander.setCompanyGroup(companyGroup);
        try {
            companyCommanderService.save(companyCommander);
            companyGroupService.save(companyGroup);
        } catch (Exception e) {
            log.info("Something went wrong in assigning CompanyCommander.");
            return false;
        }

        return true;
    }

    public BattalionGroup findBattalionGroupById(Integer id) {
        return  battalionGroupService.findBattalionGroupById(id);
    }

    public BattalionCommander findBattalionCommanderByEmail(String email) {
        Optional<BattalionCommander> tempBatCom = battalionCommanderRepository.findBattalionCommanderByEmail(email);
        if (tempBatCom.isPresent()) {
            return tempBatCom.get();
        } else {
            log.info("Error battalion commander with email" + email  + " doesn't exist.");
        }
        return null;
    }

    public BattalionCommander findBattalionCommanderById(Integer id) {
        Optional<BattalionCommander> tempBatCom = battalionCommanderRepository.findBattalionCommanderById(id);
        if (tempBatCom.isPresent()) {
            return tempBatCom.get();
        } else {
            log.info("Error battalion commander with id" + id + " doesn't exist.");
        }
        return null;
    }

    public List<SupplyRequest> getBattalionRequests() {
        BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
        return supplyRequestService.getSupplyRequestsForBattalionByBattalionId(battalionCommander.getBattalionGroup().getId());
    }

    public List<SupplyRequest> getCompaniesRequests() {
        BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
        return supplyRequestService.getSupplyRequestsForCompaniesByBattalionId(battalionCommander.getBattalionGroup().getId());
    }

    public BattalionGroupService getBattalionGroupService() {
        return battalionGroupService;
    }

    @Transactional
    public void save(BattalionCommander battalionCommander) {
        battalionCommanderRepository.save(battalionCommander);
    }

    public boolean askForResource(ResourcesRequestDTO resourcesRequestDTO) {
        BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
        ResourcesRequest resourcesRequest = ResourcesRequest.builder()
                .commanderId(battalionCommander.getId())
                .militaryGroupId(battalionCommander.getBattalionGroup().getId())
                .roleOfCommander(battalionCommander.getRole())
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
                .seniorMilitaryGroupId(battalionCommander.getBrigadeGroup().getId())
                .commanderId(battalionCommander.getId())
                .militaryGroupId(battalionCommander.getBattalionGroup().getId())
                .roleOfCommander(battalionCommander.getRole())
                .dateOfRequest(LocalDate.now())
                .status(Status.NOT_PROCESSED)
                .build();
        try {
            resourcesRequestService.save(resourcesRequest);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        resourcesRequest = resourcesRequestService.findResourcesRequestByCommanderIdAndMilitaryGroupId(battalionCommander.getId(), battalionCommander.getBattalionGroup().getId());
        supplyRequest.setResourcesRequestId(resourcesRequest);
        try {
            supplyRequestService.save(supplyRequest);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }



}
