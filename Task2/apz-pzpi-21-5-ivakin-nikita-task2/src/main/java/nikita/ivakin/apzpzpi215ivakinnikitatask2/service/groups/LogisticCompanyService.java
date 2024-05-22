package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BrigadeGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.LogisticCompanyDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.LogisticCompany;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.LogisticCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class LogisticCompanyService {

    @Autowired
    private final LogisticCompanyRepository logisticCompanyRepository;


    public boolean createLogisticCompany(LogisticCompanyDTO logisticCompanyDTO, BrigadeCommander brigadeCommander) {
        LogisticCompany logisticCompany = LogisticCompany.builder()
                .personnelCount(logisticCompanyDTO.getPersonnelCount())
                .brigadeGroup(brigadeCommander.getBrigadeGroupId())
                .ammo40mmGpCount(logisticCompanyDTO.getAmmo40mmGpCount())
                .ammo40mmRpgCount(logisticCompanyDTO.getAmmo40mmRpgCount())
                .ammo145KpvtCount(logisticCompanyDTO.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(logisticCompanyDTO.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(logisticCompanyDTO.getAmmo556x45ArCount())
                .ammo762PktCount(logisticCompanyDTO.getAmmo762PktCount())
                .ammo762x39AkCount(logisticCompanyDTO.getAmmo762x39AkCount())
                .offensiveGrenadesCount(logisticCompanyDTO.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(logisticCompanyDTO.getDefensiveGrenadesCount())
                .riflesCount(logisticCompanyDTO.getRiflesCount())
                .machineGunsCount(logisticCompanyDTO.getMachineGunsCount())
                .bodyArmorCount(logisticCompanyDTO.getBodyArmorCount())
                .helmetsCount(logisticCompanyDTO.getHelmetsCount())
                .apcCount(logisticCompanyDTO.getApcCount())
                .tankCount(logisticCompanyDTO.getTankCount())
                .foodCount(logisticCompanyDTO.getFoodCount())
                .dryRationsCount(logisticCompanyDTO.getDryRationsCount())
                .build();

        try {
            save(logisticCompany);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }

        return true;
    }


    public LogisticCompany findLogisticCompanyById(Integer id) {
        Optional<LogisticCompany> tempLogGCom = logisticCompanyRepository.findLogisticCompanyById(id);
        if (tempLogGCom.isPresent()) {
            return tempLogGCom.get();
        } else {
            log.info("Error logistic company with id" + id + " doesn't exist.");
        }
        return null;
    }

    public LogisticCompany findBrigadeGroupByBrigadeCommander(BrigadeCommander brigadeCommander) {

        Optional<LogisticCompany> tempLogComp = logisticCompanyRepository.findLogisticCompanyByBrigadeGroup(brigadeCommander.getBrigadeGroupId());
        if (tempLogComp.isPresent()) {
            return tempLogComp.get();
        } else {
            log.info("Error logistic company with brigade commander id" + brigadeCommander.getId() + " doesn't exist.");
        }
        return null;
    }


    @Transactional
    public void save(LogisticCompany logisticCompany) {
        logisticCompanyRepository.save(logisticCompany);
    }
}
