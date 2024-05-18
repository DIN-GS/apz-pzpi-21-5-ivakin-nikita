package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.CompanyCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.PlatCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.CompanyGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.PlatGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.PlatGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PlatGroupService {

    @Autowired
    private final PlatGroupRepository platGroupRepository;

    public PlatGroup fillPlatGroup(PlatGroupDTO platGroupDTO){
        PlatGroup platGroup = PlatGroup.builder()
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
        return platGroup;
    }

    public boolean createPlatGroup(PlatGroupDTO platGroupDTO, CompanyCommander companyCommander) {
        /*PlatGroup platGroup = PlatGroup.builder()
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
                .build();*/
        PlatGroup platGroup = fillPlatGroup(platGroupDTO);
        platGroup.setCompanyGroup(companyCommander.getCompanyGroup());
        try {
            save(platGroup);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }

        return true;
    }

    //Should add MapStruct
    public PlatGroup updatePlatResources(PlatGroupDTO platGroupDTO){
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
            return null;
        }
        return platGroup;
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
