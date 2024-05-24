package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BattalionGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BrigadeGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.LogisticCompanyDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.GivenResources;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.ResourcesUpdateResponse;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.LogisticCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.LogisticCompany;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.ResourcesType;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Status;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.BrigadeCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.GivenResourcesService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.BrigadeGroupService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.LogisticCompanyService;
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
public class BrigadeCommanderService {

    private final BrigadeCommanderRepository brigadeCommanderRepository;
    private final BrigadeGroupService brigadeGroupService;
    private final LogisticCompanyService logisticCompanyService;
    private final BattalionCommanderService battalionCommanderService;
    private final ResourcesRequestService resourcesRequestService;
    private final SupplyRequestService supplyRequestService;
    private final GivenResourcesService givenResourcesService;
    private final LogisticCommanderService logisticCommanderService;



    public BrigadeCommander getAuthenticatedBrigadeCommander() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String brigadeCommanderEmail = authentication.getName();
            return findBrigadeCommanderByEmail(brigadeCommanderEmail);
        } catch (Exception e) {
            throw new CommanderAuthenticationException("Error in getting authenticated brigade commander.");
        }

    }

    private boolean fillGivenResources(BrigadeGroupDTO brigadeGroupDTO, BrigadeCommander brigadeCommander){
        GivenResources givenResources = GivenResources.builder()
                .commanderId(brigadeCommander.getId())
                .militaryGroupId(brigadeCommander.getBrigadeGroupId().getId())
                .brigadeCommanderId(brigadeCommander.getId())
                .roleOfCommander(brigadeCommander.getRole())
                .ammo40mmGpCount(brigadeGroupDTO.getAmmo40mmGpCount())
                .ammo40mmRpgCount(brigadeGroupDTO.getAmmo40mmRpgCount())
                .ammo145KpvtCount(brigadeGroupDTO.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(brigadeGroupDTO.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(brigadeGroupDTO.getAmmo556x45ArCount())
                .ammo762PktCount(brigadeGroupDTO.getAmmo762PktCount())
                .ammo762x39AkCount(brigadeGroupDTO.getAmmo762x39AkCount())
                .offensiveGrenadesCount(brigadeGroupDTO.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(brigadeGroupDTO.getDefensiveGrenadesCount())
                .riflesCount(brigadeGroupDTO.getRiflesCount())
                .machineGunsCount(brigadeGroupDTO.getMachineGunsCount())
                .bodyArmorCount(brigadeGroupDTO.getBodyArmorCount())
                .helmetsCount(brigadeGroupDTO.getHelmetsCount())
                .apcCount(brigadeGroupDTO.getApcCount())
                .tankCount(brigadeGroupDTO.getTankCount())
                .foodCount(brigadeGroupDTO.getFoodCount())
                .dryRationsCount(brigadeGroupDTO.getDryRationsCount())
                .build();
        try {
            givenResourcesService.save(givenResources);
        } catch (Exception e) {
            throw new GivenResourcesCreationException("Something went wrong while creating given resources in creation of brigade.");
        }
        return true;
    }

    //TO DO: work with return statement
    public boolean createBrigade(BrigadeGroupDTO brigadeGroupDTO) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        brigadeGroupService.createBrigadeGroup(brigadeGroupDTO, brigadeCommander);
        BrigadeGroup brigadeGroup = brigadeGroupService.findBrigadeGroupByBrigadeCommander(brigadeCommander);
        brigadeCommander.setBrigadeGroupId(brigadeGroup);

        fillGivenResources(brigadeGroupDTO, brigadeCommander);

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

    public boolean assignLogisticCompanyCommander(Integer logisticCommanderId, Integer logisticCompanyId){
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        LogisticCommander logisticCommander = logisticCommanderService.findLogisticCommanderById(logisticCommanderId);
        LogisticCompany logisticCompany = logisticCompanyService.findLogisticCompanyById(logisticCompanyId);
        logisticCompany.setLogisticCommanderId(logisticCommander);
        logisticCommander.setLogisticCompany(logisticCompany);
        logisticCommander.setBrigadeCommander(brigadeCommander);
        return true;
    }

    public boolean assignBattalionCommander(Integer battalionCommanderId, Integer battalionGroupId) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        BattalionCommander battalionCommander = battalionCommanderService.findBattalionCommanderById(battalionCommanderId);
        BattalionGroup battalionGroup = battalionCommanderService.getBattalionGroupService().findBattalionGroupById(battalionGroupId);
        battalionGroup.setBattalionCommanderId(battalionCommander);
        battalionCommander.setBattalionGroup(battalionGroup);
        battalionCommander.setBrigadeCommander(brigadeCommander);
        try {
            battalionCommanderService.save(battalionCommander);
            battalionCommanderService.getBattalionGroupService().save(battalionGroup);
        } catch (Exception e) {
            throw new CommanderAssigningException("Something went wrong in assigning BattalionCommander with id" + battalionCommanderId);
        }
        return true;
    }
    @Transactional
    public void save(BrigadeCommander brigadeCommander) {
        brigadeCommanderRepository.save(brigadeCommander);
    }


    public BrigadeCommander findBrigadeCommanderByEmail(String email) {
        Optional<BrigadeCommander> tempBrigCom = brigadeCommanderRepository.findBrigadeCommanderByEmail(email);
        if (tempBrigCom.isPresent()) {
            return tempBrigCom.get();
        } else {
            throw new CommanderNotFoundException("Error brigade commander with email" + email  + " doesn't exist.");
        }
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
                .seniorMilitaryGroupId(null)
                .commanderId(brigadeCommander.getId())
                .militaryGroupId(brigadeCommander.getBrigadeGroupId().getId())
                .roleOfCommander(brigadeCommander.getRole())
                .dateOfRequest(LocalDate.now())
                .status(Status.NOT_PROCESSED)
                .build();
        try {
            resourcesRequestService.save(resourcesRequest);
        } catch (Exception e) {
            throw new ResourcesRequestCreationException("Something went wrong while creating resources request.", e);
        }
        resourcesRequest = resourcesRequestService.findResourcesRequestByCommanderIdAndMilitaryGroupId(brigadeCommander.getId(), brigadeCommander.getBrigadeGroupId().getId());
        supplyRequest.setResourcesRequestId(resourcesRequest);
        try {
            supplyRequestService.save(supplyRequest);
        } catch (Exception e) {
            throw new SupplyRequestCreationException("Something went wrong while creating supply request.", e);
        }
        return true;
    }

    public List<SupplyRequest> getBrigadeRequests() {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        return supplyRequestService.getSupplyRequestsForBrigadeByBrigadeId(brigadeCommander.getBrigadeGroupId().getId(), brigadeCommander.getRole());
    }

    public List<SupplyRequest> getBattalionRequests() {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        return supplyRequestService.getSupplyRequestsForBattalionByBrigadeId(brigadeCommander.getBrigadeGroupId().getId(), Role.BATTALION_COMMANDER);
    }

    public boolean validateResources(BrigadeGroupDTO brigadeGroupDTO) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        GivenResources givenResources = givenResourcesService.getGivenResources(
                brigadeCommander.getId(), brigadeCommander.getBrigadeGroupId().getId(), brigadeCommander.getRole(), brigadeCommander.getId(), ResourcesType.FOR_PERFORMING_A_MISSION
        );
        return brigadeGroupDTO.getAmmo762PktCount() >= givenResources.getAmmo762PktCount() / 4 && brigadeGroupDTO.getAmmo556x45ArCount() >= givenResources.getAmmo556x45ArCount() / 4
                && brigadeGroupDTO.getAmmo545x39AkRpkCount() >= givenResources.getAmmo545x39AkRpkCount() / 4 && brigadeGroupDTO.getAmmo762x39AkCount() >= givenResources.getAmmo762x39AkCount() / 4
                && brigadeGroupDTO.getAmmo145KpvtCount() >= givenResources.getAmmo145KpvtCount() / 4 && brigadeGroupDTO.getAmmo40mmGpCount() >= givenResources.getAmmo40mmGpCount() / 4
                && brigadeGroupDTO.getAmmo40mmRpgCount() >= givenResources.getAmmo40mmRpgCount() / 4 && brigadeGroupDTO.getBodyArmorCount() >= givenResources.getBodyArmorCount()
                && brigadeGroupDTO.getHelmetsCount() >= givenResources.getHelmetsCount() && brigadeGroupDTO.getApcCount() >= givenResources.getApcCount();
    }

    public ResourcesUpdateResponse updateBrigadeResources(BrigadeGroupDTO brigadeGroupDTO) {
        boolean validationResult;
        boolean updateResult;
        try {
            validationResult = !validateResources(brigadeGroupDTO);
            updateResult = brigadeGroupService.updateBrigadeResources(brigadeGroupDTO);
        } catch (Exception e) {
            throw new MilitaryGroupUpdateException("Error updating resources in brigade.");
        }

        return new ResourcesUpdateResponse(updateResult, validationResult);
    }

    public ResourcesUpdateResponse sendResourcesToBattalion(SupplyRequest supplyRequest) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        if (supplyRequest.getBrigadeCommanderId().equals(brigadeCommander.getId()) && supplyRequest.getSeniorMilitaryGroupId().equals(brigadeCommander.getBrigadeGroupId().getId())
                && supplyRequest.getRoleOfCommander().equals(Role.BATTALION_COMMANDER)){
            BattalionGroup battalionGroup = battalionCommanderService.getBattalionGroupService().findBattalionGroupById(supplyRequest.getMilitaryGroupId());
            return allocateResources(supplyRequest.getResourcesRequestId(), brigadeCommander.getBrigadeGroupId(), brigadeCommander, battalionGroup);
        }
        return new ResourcesUpdateResponse(false, false);
    }

    public ResourcesUpdateResponse allocateResources(ResourcesRequest resourcesRequest, BrigadeGroup brigadeGroup, BrigadeCommander brigadeCommander, BattalionGroup battalionGroup) {
        boolean needForSupply = false;
        try {
            givenResourcesService.allocateResources(resourcesRequest, brigadeGroup, battalionGroup,
                    battalionGroup.getBattalionCommanderId().getId(), battalionGroup.getBattalionCommanderId().getRole(), battalionGroup.getBattalionCommanderId().getBrigadeCommander().getId());
        } catch (Exception e) {
            throw new GivenResourcesCreationException("Something went wrong in allocation resources for battalion group");
        }

        return new ResourcesUpdateResponse(true, needForSupply);
    }

    public List<BattalionGroup> getBrigadeBattalionGroups() {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        return battalionCommanderService.getBattalionGroupService().findBattalionsByBrigadeGroupId(brigadeCommander.getBrigadeGroupId());

    }

}

