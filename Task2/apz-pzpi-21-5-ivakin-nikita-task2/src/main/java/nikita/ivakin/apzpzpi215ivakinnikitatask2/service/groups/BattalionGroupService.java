package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BattalionGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.BattalionCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.BattalionGroupRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.BattalionCommanderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BattalionGroupService {

    @Autowired
    private final BattalionGroupRepository battalionGroupRepository;

    public boolean createBattalionGroup(BattalionGroupDTO battalionGroupDTO, BrigadeCommander brigadeCommander) {
        BattalionGroup battalionGroup = BattalionGroup.builder()
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

        return true;
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


    @Transactional
    public void save(BattalionGroup battalionGroup) {
        battalionGroupRepository.save(battalionGroup);
    }
}

