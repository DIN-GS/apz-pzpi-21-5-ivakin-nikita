package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BrigadeGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.BrigadeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BrigadeGroupService {

    @Autowired
    private final BrigadeGroupRepository brigadeGroupRepository;

    public boolean createBrigadeGroup(BrigadeGroupDTO brigadeGroupDTO, BrigadeCommander brigadeCommander) {
        BrigadeGroup brigadeGroup = BrigadeGroup.builder()
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
                .bodyArmorCount(brigadeGroupDTO.getBodyArmorCount())
                .helmetsCount(brigadeGroupDTO.getHelmetsCount())
                .apcCount(brigadeGroupDTO.getApcCount())
                .tankCount(brigadeGroupDTO.getTankCount())
                .build();

        try {
            save(brigadeGroup);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }

        return true;
    }

    @Transactional
    public void save(BrigadeGroup brigadeGroup) {
        brigadeGroupRepository.save(brigadeGroup);
    }
}
