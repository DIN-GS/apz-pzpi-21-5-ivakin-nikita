package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.CompanyGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.GivenResources;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.CompanyGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.CompanyGroupRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.GivenResourcesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CompanyGroupService {

    private final CompanyGroupRepository companyGroupRepository;
    private final GivenResourcesService givenResourcesService;

    private boolean fillGivenResources(CompanyGroup companyGroup, BattalionCommander battalionCommander){
        GivenResources givenResources = GivenResources.builder()
                .militaryGroupId(companyGroup.getId())
                .brigadeCommanderId(battalionCommander.getBrigadeCommander().getId())
                .roleOfCommander(Role.COMPANY_COMMANDER)
                .ammo40mmGpCount(companyGroup.getAmmo40mmGpCount())
                .ammo40mmRpgCount(companyGroup.getAmmo40mmRpgCount())
                .ammo145KpvtCount(companyGroup.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(companyGroup.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(companyGroup.getAmmo556x45ArCount())
                .ammo762PktCount(companyGroup.getAmmo762PktCount())
                .ammo762x39AkCount(companyGroup.getAmmo762x39AkCount())
                .offensiveGrenadesCount(companyGroup.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(companyGroup.getDefensiveGrenadesCount())
                .riflesCount(companyGroup.getRiflesCount())
                .machineGunsCount(companyGroup.getMachineGunsCount())
                .bodyArmorCount(companyGroup.getBodyArmorCount())
                .helmetsCount(companyGroup.getHelmetsCount())
                .apcCount(companyGroup.getApcCount())
                .tankCount(companyGroup.getTankCount())
                .foodCount(companyGroup.getFoodCount())
                .dryRationsCount(companyGroup.getDryRationsCount())
                .build();
        try {
            givenResourcesService.save(givenResources);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean addGivenResourcesForCompany(BattalionCommander battalionCommander){
        ArrayList<CompanyGroup> companyGroups = companyGroupRepository.findAll();
        if (companyGroups.size() > 0) {
            CompanyGroup companyGroup = companyGroups.get(companyGroups.size()-1);
            try {
                fillGivenResources(companyGroup, battalionCommander);
            } catch (Exception e) {
                log.info("Error creating given resources entity.");
                return false;
            }
        } else {
            log.info("Error: there aren't any companies for giving resources.");
            return false;
        }
        return true;
    }

    public boolean createCompanyGroup(CompanyGroupDTO companyGroupDTO, BattalionCommander battalionCommander) {
        CompanyGroup companyGroup = CompanyGroup.builder()
                .battalionGroup(battalionCommander.getBattalionGroup())
                .personnelCount(companyGroupDTO.getPersonnelCount())
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

        return addGivenResourcesForCompany(battalionCommander);
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

    public CompanyGroup updateCompanyResources(CompanyGroupDTO companyGroupDTO) {
        CompanyGroup companyGroup = findCompanyGroupById(companyGroupDTO.getId());
        companyGroup.setAmmo40mmGpCount(companyGroupDTO.getAmmo40mmGpCount());
        companyGroup.setAmmo40mmRpgCount(companyGroupDTO.getAmmo40mmRpgCount());
        companyGroup.setAmmo145KpvtCount(companyGroupDTO.getAmmo145KpvtCount());
        companyGroup.setAmmo545x39AkRpkCount(companyGroupDTO.getAmmo545x39AkRpkCount());
        companyGroup.setAmmo762PktCount(companyGroupDTO.getAmmo762PktCount());
        companyGroup.setAmmo556x45ArCount(companyGroupDTO.getAmmo556x45ArCount());
        companyGroup.setAmmo762x39AkCount(companyGroupDTO.getAmmo762x39AkCount());
        companyGroup.setOffensiveGrenadesCount(companyGroupDTO.getOffensiveGrenadesCount());
        companyGroup.setDefensiveGrenadesCount(companyGroupDTO.getDefensiveGrenadesCount());
        companyGroup.setRiflesCount(companyGroupDTO.getRiflesCount());
        companyGroup.setMachineGunsCount(companyGroup.getMachineGunsCount());
        companyGroup.setBodyArmorCount(companyGroupDTO.getBodyArmorCount());
        companyGroup.setHelmetsCount(companyGroupDTO.getHelmetsCount());
        companyGroup.setApcCount(companyGroupDTO.getApcCount());
        companyGroup.setTankCount(companyGroupDTO.getTankCount());
        try {
            companyGroupRepository.save(companyGroup);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
        return companyGroup;
    }


    @Transactional
    public void save(CompanyGroup companyGroup) {
        companyGroupRepository.save(companyGroup);
    }


}
