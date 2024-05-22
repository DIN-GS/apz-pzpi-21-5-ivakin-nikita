package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BattalionGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.GivenResources;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.BattalionGroupRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.GivenResourcesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BattalionGroupService {

    private final BattalionGroupRepository battalionGroupRepository;
    private final GivenResourcesService givenResourcesService;

    private boolean fillGivenResources(BattalionGroup battalionGroup, BrigadeCommander brigadeCommander){
        GivenResources givenResources = GivenResources.builder()
                .militaryGroupId(battalionGroup.getId())
                .brigadeCommanderId(brigadeCommander.getId())
                .roleOfCommander(Role.BATTALION_COMMANDER)
                .ammo40mmGpCount(battalionGroup.getAmmo40mmGpCount())
                .ammo40mmRpgCount(battalionGroup.getAmmo40mmRpgCount())
                .ammo145KpvtCount(battalionGroup.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(battalionGroup.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(battalionGroup.getAmmo556x45ArCount())
                .ammo762PktCount(battalionGroup.getAmmo762PktCount())
                .ammo762x39AkCount(battalionGroup.getAmmo762x39AkCount())
                .offensiveGrenadesCount(battalionGroup.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(battalionGroup.getDefensiveGrenadesCount())
                .riflesCount(battalionGroup.getRiflesCount())
                .machineGunsCount(battalionGroup.getMachineGunsCount())
                .bodyArmorCount(battalionGroup.getBodyArmorCount())
                .helmetsCount(battalionGroup.getHelmetsCount())
                .apcCount(battalionGroup.getApcCount())
                .tankCount(battalionGroup.getTankCount())
                .foodCount(battalionGroup.getFoodCount())
                .dryRationsCount(battalionGroup.getDryRationsCount())
                .build();
        try {
            givenResourcesService.save(givenResources);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean addGivenResourcesForBattalion(BrigadeCommander brigadeCommander){
        ArrayList<BattalionGroup> battalionGroups = battalionGroupRepository.findAll();
        if (battalionGroups.size() > 0) {
            BattalionGroup battalionGroup = battalionGroups.get(battalionGroups.size()-1);
            try {
                fillGivenResources(battalionGroup, brigadeCommander);
            } catch (Exception e) {
                log.info("Error creating given resources entity.");
                return false;
            }
        } else {
            log.info("Error: there aren't any battalions for giving resources.");
            return false;
        }
        return true;
    }

    public boolean createBattalionGroup(BattalionGroupDTO battalionGroupDTO, BrigadeCommander brigadeCommander) {
        BattalionGroup battalionGroup = BattalionGroup.builder()
                .personnelCount(battalionGroupDTO.getPersonnelCount())
                .brigadeGroup(brigadeCommander.getBrigadeGroupId())
                .ammo40mmGpCount(battalionGroupDTO.getAmmo40mmGpCount())
                .ammo40mmRpgCount(battalionGroupDTO.getAmmo40mmRpgCount())
                .ammo145KpvtCount(battalionGroupDTO.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(battalionGroupDTO.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(battalionGroupDTO.getAmmo556x45ArCount())
                .ammo762PktCount(battalionGroupDTO.getAmmo762PktCount())
                .ammo762x39AkCount(battalionGroupDTO.getAmmo762x39AkCount())
                .offensiveGrenadesCount(battalionGroupDTO.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(battalionGroupDTO.getDefensiveGrenadesCount())
                .riflesCount(battalionGroupDTO.getRiflesCount())
                .bodyArmorCount(battalionGroupDTO.getBodyArmorCount())
                .helmetsCount(battalionGroupDTO.getHelmetsCount())
                .apcCount(battalionGroupDTO.getApcCount())
                .tankCount(battalionGroupDTO.getTankCount())
                .build();

        try {
            save(battalionGroup);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }

        return addGivenResourcesForBattalion(brigadeCommander);

    }

    /*public boolean assignBattalionCommander(Integer batComId, Integer battalionGroupId) {
        BattalionCommander battalionCommander = battalionCommanderService.findBattalionCommanderById(batComId);
        BattalionGroup battalionGroup = findBattalionGroupById(battalionGroupId);
        battalionGroup.setBattalionCommanderId(battalionCommander);
        try {
            save(battalionGroup);
        } catch (Exception e) {
            log.info("Error");
            return false;
        }
        return true;
    }*/

    public BattalionGroup findBattalionGroupById(Integer id) {
        Optional<BattalionGroup> tempBatGroup = battalionGroupRepository.findBattalionGroupById(id);
        if (tempBatGroup.isPresent()) {
            return tempBatGroup.get();
        } else {
            log.info("Error battalion group with id" + id + " doesn't exist.");
        }
        return null;
    }

    public boolean updateBattalionResources(BattalionGroupDTO battalionGroupDTO) {
        BattalionGroup battalionGroup = findBattalionGroupById(battalionGroupDTO.getId());
        battalionGroup.setAmmo40mmGpCount(battalionGroupDTO.getAmmo40mmGpCount());
        battalionGroup.setAmmo40mmRpgCount(battalionGroupDTO.getAmmo40mmRpgCount());
        battalionGroup.setAmmo145KpvtCount(battalionGroupDTO.getAmmo145KpvtCount());
        battalionGroup.setAmmo545x39AkRpkCount(battalionGroupDTO.getAmmo545x39AkRpkCount());
        battalionGroup.setAmmo762PktCount(battalionGroupDTO.getAmmo762PktCount());
        battalionGroup.setAmmo556x45ArCount(battalionGroupDTO.getAmmo556x45ArCount());
        battalionGroup.setAmmo762x39AkCount(battalionGroupDTO.getAmmo762x39AkCount());
        battalionGroup.setOffensiveGrenadesCount(battalionGroupDTO.getOffensiveGrenadesCount());
        battalionGroup.setDefensiveGrenadesCount(battalionGroupDTO.getDefensiveGrenadesCount());
        battalionGroup.setRiflesCount(battalionGroupDTO.getRiflesCount());
        battalionGroup.setMachineGunsCount(battalionGroup.getMachineGunsCount());
        battalionGroup.setBodyArmorCount(battalionGroupDTO.getBodyArmorCount());
        battalionGroup.setHelmetsCount(battalionGroupDTO.getHelmetsCount());
        battalionGroup.setApcCount(battalionGroupDTO.getApcCount());
        battalionGroup.setTankCount(battalionGroupDTO.getTankCount());
        try {
            battalionGroupRepository.save(battalionGroup);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }


    @Transactional
    public void save(BattalionGroup battalionGroup) {
        battalionGroupRepository.save(battalionGroup);
    }


}

