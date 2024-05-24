package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.GivenResources;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesUpdateResponse;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.PlatCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.PlatGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.ResourcesType;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Status;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.PlatCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.GivenResourcesService;
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
    private final GivenResourcesService givenResourcesService;

    public PlatCommander getAuthenticatedPlatCommander() {
        try {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String platCommanderEmail = authentication.getName();
        return findPlatCommanderByEmail(platCommanderEmail);
        } catch (Exception e) {
            throw new CommanderAuthenticationException("Error in getting authenticated brigade commander.");
        }
    }

    public PlatCommander findPlatCommanderById(Integer id){
        Optional<PlatCommander> tempPlatCom = platCommanderRepository.findPlatCommanderById(id);
        if (tempPlatCom.isPresent()) {
            return tempPlatCom.get();
        } else {
            throw new CommanderNotFoundException("Error plat commander with id" + id + " doesn't exist.");
        }
    }

    public PlatCommander findPlatCommanderByEmail(String email) {
        Optional<PlatCommander> tempPlatCom = platCommanderRepository.findPlatCommanderByEmail(email);
        if (tempPlatCom.isPresent()) {
            return tempPlatCom.get();
        } else {
            throw new CommanderNotFoundException("Error plat commander with email" + email  + " doesn't exist.");
        }
    }

    public PlatGroupDTO getPlatGroup(){
        PlatCommander platCommander = getAuthenticatedPlatCommander();
        PlatGroup platGroup = platGroupService.findPlatGroupByPlatCommander(platCommander);
        return platGroupService.mapPlatGroupToDTO(platGroup);
    }

    //Validation
    public boolean validateResources(PlatGroupDTO platGroupDTO) {
        PlatCommander platCommander = getAuthenticatedPlatCommander();
        GivenResources givenResources = givenResourcesService.getGivenResources(
                platCommander.getId(), platCommander.getPlatGroup().getId(), platCommander.getRole(), platCommander.getBrigadeCommanderId(), ResourcesType.FOR_PERFORMING_A_MISSION
        );
        return platGroupDTO.getAmmo762PktCount() >= givenResources.getAmmo762PktCount() / 4 && platGroupDTO.getAmmo556x45ArCount() >= givenResources.getAmmo556x45ArCount() / 4
                && platGroupDTO.getAmmo545x39AkRpkCount() >= givenResources.getAmmo545x39AkRpkCount() / 4 && platGroupDTO.getAmmo762x39AkCount() >= givenResources.getAmmo762x39AkCount() / 4
                && platGroupDTO.getAmmo145KpvtCount() >= givenResources.getAmmo145KpvtCount() / 4 && platGroupDTO.getAmmo40mmGpCount() >= givenResources.getAmmo40mmGpCount() / 4
                && platGroupDTO.getAmmo40mmRpgCount() >= givenResources.getAmmo40mmRpgCount() / 4 && platGroupDTO.getBodyArmorCount() >= givenResources.getBodyArmorCount()
                && platGroupDTO.getHelmetsCount() >= givenResources.getHelmetsCount() && platGroupDTO.getApcCount() >= givenResources.getApcCount();
    }

    //Add check of resources
    public ResourcesUpdateResponse updatePlatResources(PlatGroupDTO platGroupDTO) {
        try {
            boolean validationResult = !validateResources(platGroupDTO);
            boolean updateResult = platGroupService.updatePlatResources(platGroupDTO);
            return new ResourcesUpdateResponse(updateResult, validationResult);
        } catch (Exception e) {
            throw new MilitaryGroupUpdateException("Something went wrong in updating plat resources.");
        }
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
            throw new ResourcesRequestCreationException("Something went wrong in creation resources request in plat commander method ask for resources.");
        }
        resourcesRequest = resourcesRequestService.findResourcesRequestByCommanderIdAndMilitaryGroupId(platCommander.getId(), platCommander.getPlatGroup().getId());
        supplyRequest.setResourcesRequestId(resourcesRequest);
        try {
            supplyRequestService.save(supplyRequest);
        } catch (Exception e) {
            throw new SupplyRequestCreationException("Something went wrong in creation supply request in plat commander method ask for resources.");
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
