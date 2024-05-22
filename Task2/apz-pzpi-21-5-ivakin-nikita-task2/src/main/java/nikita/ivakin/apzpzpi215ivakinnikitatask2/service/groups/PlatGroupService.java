package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.GivenResources;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.CompanyCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.PlatCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.PlatGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.PlatGroupRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.GivenResourcesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PlatGroupService {

    private final PlatGroupRepository platGroupRepository;
    private final GivenResourcesService givenResourcesService;

    private boolean fillGivenResources(PlatGroup platGroup, CompanyCommander companyCommander){
        GivenResources givenResources = GivenResources.builder()
                .militaryGroupId(platGroup.getId())
                .brigadeCommanderId(companyCommander.getBrigadeCommanderId())
                .roleOfCommander(Role.PLAT_COMMANDER)
                .ammo40mmGpCount(platGroup.getAmmo40mmGpCount())
                .ammo40mmRpgCount(platGroup.getAmmo40mmRpgCount())
                .ammo145KpvtCount(platGroup.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(platGroup.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(platGroup.getAmmo556x45ArCount())
                .ammo762PktCount(platGroup.getAmmo762PktCount())
                .ammo762x39AkCount(platGroup.getAmmo762x39AkCount())
                .offensiveGrenadesCount(platGroup.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(platGroup.getDefensiveGrenadesCount())
                .riflesCount(platGroup.getRiflesCount())
                .machineGunsCount(platGroup.getMachineGunsCount())
                .bodyArmorCount(platGroup.getBodyArmorCount())
                .helmetsCount(platGroup.getHelmetsCount())
                .apcCount(platGroup.getApcCount())
                .tankCount(platGroup.getTankCount())
                .foodCount(platGroup.getFoodCount())
                .dryRationsCount(platGroup.getDryRationsCount())
                .build();
        try {
            givenResourcesService.save(givenResources);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean addGivenResourcesForPlat(CompanyCommander companyCommander){
        ArrayList<PlatGroup> platGroups = platGroupRepository.findAll();
        if (platGroups.size() > 0) {
            PlatGroup platGroup = platGroups.get(platGroups.size()-1);
            try {
                fillGivenResources(platGroup, companyCommander);
            } catch (Exception e) {
                log.info("Error creating given resources entity.");
                return false;
            }
        } else {
            log.info("Error: there aren't any plats for giving resources.");
            return false;
        }
        return true;
    }


    public boolean createPlatGroup(PlatGroupDTO platGroupDTO, CompanyCommander companyCommander) {
        PlatGroup platGroup = PlatGroup.builder()
                .companyGroup(companyCommander.getCompanyGroup())
                .ammo40mmGpCount(platGroupDTO.getAmmo40mmGpCount())
                .ammo40mmRpgCount(platGroupDTO.getAmmo40mmRpgCount())
                .ammo145KpvtCount(platGroupDTO.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(platGroupDTO.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(platGroupDTO.getAmmo556x45ArCount())
                .ammo762PktCount(platGroupDTO.getAmmo762PktCount())
                .ammo762x39AkCount(platGroupDTO.getAmmo762x39AkCount())
                .offensiveGrenadesCount(platGroupDTO.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(platGroupDTO.getDefensiveGrenadesCount())
                .riflesCount(platGroupDTO.getRiflesCount())
                .bodyArmorCount(platGroupDTO.getBodyArmorCount())
                .helmetsCount(platGroupDTO.getHelmetsCount())
                .apcCount(platGroupDTO.getApcCount())
                .tankCount(platGroupDTO.getTankCount())
                .build();
        platGroup.setCompanyGroup(companyCommander.getCompanyGroup());
        try {
            save(platGroup);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }

        return addGivenResourcesForPlat(companyCommander);
    }

    //Should add MapStruct
    public boolean updatePlatResources(PlatGroupDTO platGroupDTO){
        PlatGroup platGroup = findPlatGroupById(platGroupDTO.getId());
        platGroup.setAmmo40mmGpCount(platGroupDTO.getAmmo40mmGpCount());
        platGroup.setAmmo40mmRpgCount(platGroupDTO.getAmmo40mmRpgCount());
        platGroup.setAmmo145KpvtCount(platGroupDTO.getAmmo145KpvtCount());
        platGroup.setAmmo545x39AkRpkCount(platGroupDTO.getAmmo545x39AkRpkCount());
        platGroup.setAmmo762PktCount(platGroupDTO.getAmmo762PktCount());
        platGroup.setAmmo556x45ArCount(platGroupDTO.getAmmo556x45ArCount());
        platGroup.setAmmo762x39AkCount(platGroupDTO.getAmmo762x39AkCount());
        platGroup.setOffensiveGrenadesCount(platGroupDTO.getOffensiveGrenadesCount());
        platGroup.setDefensiveGrenadesCount(platGroupDTO.getDefensiveGrenadesCount());
        platGroup.setRiflesCount(platGroupDTO.getRiflesCount());
        platGroup.setMachineGunsCount(platGroup.getMachineGunsCount());
        platGroup.setBodyArmorCount(platGroupDTO.getBodyArmorCount());
        platGroup.setHelmetsCount(platGroupDTO.getHelmetsCount());
        platGroup.setApcCount(platGroupDTO.getApcCount());
        platGroup.setTankCount(platGroupDTO.getTankCount());
        try {
            platGroupRepository.save(platGroup);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }

    public PlatGroup findPlatGroupByPlatCommander(PlatCommander platCommander) {
        Optional<PlatGroup> tempPlatGroup = platGroupRepository.findPlatGroupByPlatCommanderId(platCommander);
        if (tempPlatGroup.isPresent()) {
            return tempPlatGroup.get();
        } else {
            log.info("Error plat group with plat commander id" + platCommander.getId() + " doesn't exist.");
        }
        return null;
    }

    public PlatGroup findPlatGroupById(Integer id) {
        Optional<PlatGroup> tempPlatGroup = platGroupRepository.findPlatGroupById(id);
        if (tempPlatGroup.isPresent()) {
            return tempPlatGroup.get();
        } else {
            log.info("Error plat group with id" + id + " doesn't exist.");
        }
        return null;
    }

    @Transactional
    public void save(PlatGroup platGroup) {
        platGroupRepository.save(platGroup);
    }
}
