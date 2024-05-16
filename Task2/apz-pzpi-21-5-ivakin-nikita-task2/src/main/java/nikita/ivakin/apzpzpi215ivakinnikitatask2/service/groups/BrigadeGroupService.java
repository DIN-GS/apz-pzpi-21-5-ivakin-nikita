package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BrigadeGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.BrigadeGroupRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.BrigadeCommanderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BrigadeGroupService {

    @Autowired
    private final BrigadeGroupRepository brigadeGroupRepository;
    /*@Autowired
    private final BrigadeCommanderService brigadeCommanderService;*/

    public boolean createBrigadeGroup(BrigadeGroupDTO brigadeGroupDTO, BrigadeCommander brigadeCommander) {
        BrigadeGroup brigadeGroup = BrigadeGroup.builder()
                .personnelCount(brigadeGroupDTO.getPersonnelCount())
                .brigadeCommanderId(brigadeCommander)
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
            save(brigadeGroup);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }

        return true;
    }

    public BrigadeGroup findBrigadeGroupByBrigadeCommander(BrigadeCommander brigadeCommanderId) {

        Optional<BrigadeGroup> tempBrigGroup = brigadeGroupRepository.findBrigadeGroupByBrigadeCommanderId(brigadeCommanderId);
        if (tempBrigGroup.isPresent()) {
            return tempBrigGroup.get();
        } else {
            log.info("Error brigade group with brigade commander id" + brigadeCommanderId.getId() + " doesn't exist.");
        }
        return null;
    }

    @Transactional
    public void save(BrigadeGroup brigadeGroup) {
        brigadeGroupRepository.save(brigadeGroup);
    }
}
