package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.PlatCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.PlatGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Status;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.PlatCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.PlatGroupService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests.ResourcesRequestService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests.SupplyRequestService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PlatCommanderService {


    private final PlatCommanderRepository platCommanderRepository;
    private final PlatGroupService platGroupService;
    private final ResourcesRequestService resourcesRequestService;
    private final SupplyRequestService supplyRequestService;

    public PlatCommander getAuthenticatedPlatCommander() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String platCommanderEmail = authentication.getName();
        return findPlatCommanderByEmail(platCommanderEmail);
    }

    public PlatCommander findPlatCommanderById(Integer id){
        Optional<PlatCommander> tempPlatCom = platCommanderRepository.findPlatCommanderById(id);
        if (tempPlatCom.isPresent()) {
            return tempPlatCom.get();
        } else {
            log.info("Error plat commander with id" + id + " doesn't exist.");
        }
        return null;
    }

    public PlatCommander findPlatCommanderByEmail(String email) {
        Optional<PlatCommander> tempPlatCom = platCommanderRepository.findPlatCommanderByEmail(email);
        if (tempPlatCom.isPresent()) {
            return tempPlatCom.get();
        } else {
            log.info("Error plat commander with email" + email  + " doesn't exist.");
        }
        return null;
    }

    public PlatGroup getPlatGroup(){
        PlatCommander platCommander = getAuthenticatedPlatCommander();
        return platGroupService.findPlatGroupByPlatCommander(platCommander);
    }

    //Validation
    public boolean validateResources(PlatGroupDTO platGroupDTO) {
        PlatCommander platCommander = getAuthenticatedPlatCommander();
        PlatGroup platGroup = platGroupService.findPlatGroupById(platCommander.getPlatGroup().getId());
        //if (platGroupDTO.getAmmo40mmGpCount() < )
        return true;
    }

    //Add check of resources
    public PlatGroup updatePlatResources(PlatGroupDTO platGroupDTO) {
        validateResources(platGroupDTO);
        return platGroupService.updatePlatResources(platGroupDTO);
    }

    public boolean askForResources(ResourcesRequestDTO resourcesRequestDTO) {
        PlatCommander platCommander = getAuthenticatedPlatCommander();
        ResourcesRequest resourcesRequest = ResourcesRequest.builder()
                .commanderId(platCommander.getId())
                .militaryGroupId(platCommander.getPlatGroup().getId())
                .roleOfCommander(platCommander.getRole())
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
                .brigadeCommanderId(platCommander.getBrigadeCommanderId())
                .seniorMilitaryGroupId(platCommander.getCompanyGroup().getId())
                .commanderId(platCommander.getId())
                .militaryGroupId(platCommander.getPlatGroup().getId())
                .roleOfCommander(platCommander.getRole())
                .dateOfRequest(LocalDate.now())
                .status(Status.NOT_PROCESSED)
                .build();
        try {
            resourcesRequestService.save(resourcesRequest);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        resourcesRequest = resourcesRequestService.findResourcesRequestByCommanderIdAndMilitaryGroupId(platCommander.getId(), platCommander.getPlatGroup().getId());
        supplyRequest.setResourcesRequestId(resourcesRequest);
        try {
            supplyRequestService.save(supplyRequest);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }

    public List<SupplyRequest> getPlatRequests() {
        PlatCommander platCommander = getAuthenticatedPlatCommander();
        return supplyRequestService.getSupplyRequestsForPlatByPlatId(platCommander.getPlatGroup().getId(), platCommander.getRole());
    }

    @Transactional
    public void save(PlatCommander platCommander) {
        platCommanderRepository.save(platCommander);
    }



}
