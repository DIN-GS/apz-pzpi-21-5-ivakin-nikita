package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.CompanyCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.PlatCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.PlatGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Status;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.CompanyCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.PlatGroupService;
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
public class CompanyCommanderService {

    @Autowired
    private final CompanyCommanderRepository companyCommanderRepository;
    @Autowired
    private final PlatGroupService platGroupService;
    @Autowired
    private final PlatCommanderService platCommanderService;
    @Autowired
    private final ResourcesRequestService resourcesRequestService;
    @Autowired
    private final SupplyRequestService supplyRequestService;



    public CompanyCommander getAuthenticatedCompanyCommander() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String companyCommanderEmail = authentication.getName();
        return findCompanyCommanderByEmail(companyCommanderEmail);
    }

    public boolean createPlat(PlatGroupDTO platGroupDTO) {
        CompanyCommander companyCommander = getAuthenticatedCompanyCommander();
        return platGroupService.createPlatGroup(platGroupDTO, companyCommander);
    }

    public boolean assignPlatCommander(Integer platCommanderId, Integer platGroupId) {
        CompanyCommander companyCommander = getAuthenticatedCompanyCommander();
        PlatCommander platCommander = platCommanderService.findPlatCommanderById(platCommanderId);
        PlatGroup platGroup = platGroupService.findPlatGroupById(platGroupId);
        platGroup.setPlatCommanderId(platCommander);
        platCommander.setPlatGroup(platGroup);
        platCommander.setCompanyCommander(companyCommander);
        platCommander.setCompanyGroup(companyCommander.getCompanyGroup());
        try {
            platCommanderService.save(platCommander);
            platGroupService.save(platGroup);
        } catch (Exception e) {
            log.info("Something went wrong in assigning PlatCommander.");
            return false;
        }

        return true;
    }

    public CompanyCommander findCompanyCommanderById(Integer id) {
        Optional<CompanyCommander> tempCompCom = companyCommanderRepository.findCompanyCommanderById(id);
        if (tempCompCom.isPresent()) {
            return tempCompCom.get();
        } else {
            log.info("Error company commander with id" + id + " doesn't exist.");
        }
        return null;
    }

    public CompanyCommander findCompanyCommanderByEmail(String email) {
        Optional<CompanyCommander> tempCompCom = companyCommanderRepository.findCompanyCommanderByEmail(email);
        if (tempCompCom.isPresent()) {
            return tempCompCom.get();
        } else {
            log.info("Error company commander with email" + email  + " doesn't exist.");
        }
        return null;
    }

    @Transactional
    public void save(CompanyCommander companyCommander) {
        companyCommanderRepository.save(companyCommander);
    }

    public boolean askForResources(ResourcesRequestDTO resourcesRequestDTO) {
        CompanyCommander companyCommander = getAuthenticatedCompanyCommander();
        ResourcesRequest resourcesRequest = ResourcesRequest.builder()
                .commanderId(companyCommander.getId())
                .militaryGroupId(companyCommander.getCompanyGroup().getId())
                .roleOfCommander(companyCommander.getRole())
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
                .seniorMilitaryGroupId(companyCommander.getBattalionGroup().getId())
                .commanderId(companyCommander.getId())
                .militaryGroupId(companyCommander.getCompanyGroup().getId())
                .roleOfCommander(companyCommander.getRole())
                .dateOfRequest(LocalDate.now())
                .status(Status.NOT_PROCESSED)
                .build();
        try {
            resourcesRequestService.save(resourcesRequest);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        resourcesRequest = resourcesRequestService.findResourcesRequestByCommanderIdAndMilitaryGroupId(companyCommander.getId(), companyCommander.getCompanyGroup().getId());
        supplyRequest.setResourcesRequestId(resourcesRequest);
        try {
            supplyRequestService.save(supplyRequest);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }

    public List<SupplyRequest> getCompanyRequests() {
        CompanyCommander companyCommander = getAuthenticatedCompanyCommander();
        return supplyRequestService.getSupplyRequestsForCompanyByCompanyId(companyCommander.getCompanyGroup().getId(), companyCommander.getRole());
    }

    public List<SupplyRequest> getPlatsRequests() {
        CompanyCommander companyCommander = getAuthenticatedCompanyCommander();
        return supplyRequestService.getSupplyRequestsForPlatsByCompanyId(companyCommander.getCompanyGroup().getId(), Role.PLAT_COMMANDER);
    }
}

