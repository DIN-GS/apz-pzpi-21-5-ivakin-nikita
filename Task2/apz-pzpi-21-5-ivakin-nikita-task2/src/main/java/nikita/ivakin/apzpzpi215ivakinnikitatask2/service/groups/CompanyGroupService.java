package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.CompanyGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.CompanyGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.CompanyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CompanyGroupService {

    @Autowired
    private final CompanyGroupRepository companyGroupRepository;

    public boolean createCompanyGroup(CompanyGroupDTO companyGroupDTO, BattalionCommander battalionCommander) {
        CompanyGroup companyGroup = CompanyGroup.builder()
                .battalionGroup(battalionCommander.getBattalionGroup())
                .ammo40mmGpCount(companyGroupDTO.getAmmo40mmGpCount())
                .ammo40mmRpgCount(companyGroupDTO.getAmmo40mmRpgCount())
                .ammo145KpvtCount(companyGroupDTO.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(companyGroupDTO.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(companyGroupDTO.getAmmo556x45ArCount())
                .ammo762PktCount(companyGroupDTO.getAmmo762PktCount())
                .ammo762x39AkCount(companyGroupDTO.getAmmo762x39AkCount())
                .offensiveGrenadesCount(companyGroupDTO.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(companyGroupDTO.getDefensiveGrenadesCount())
                .riflesCount(companyGroupDTO.getRiflesCount())
                .bodyArmorCount(companyGroupDTO.getBodyArmorCount())
                .helmetsCount(companyGroupDTO.getHelmetsCount())
                .apcCount(companyGroupDTO.getApcCount())
                .tankCount(companyGroupDTO.getTankCount())
                .build();
        try {
            save(companyGroup);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }

        return true;
    }

    public CompanyGroup findCompanyGroupById(Integer id) {
        Optional<CompanyGroup> tempCompGroup = companyGroupRepository.findCompanyGroupById(id);
        if (tempCompGroup.isPresent()) {
            return tempCompGroup.get();
        } else {
            log.info("Error battalion group with id" + id + " doesn't exist.");
        }
        return null;
    }

    @Transactional
    public void save(CompanyGroup companyGroup) {
        companyGroupRepository.save(companyGroup);
    }
}
