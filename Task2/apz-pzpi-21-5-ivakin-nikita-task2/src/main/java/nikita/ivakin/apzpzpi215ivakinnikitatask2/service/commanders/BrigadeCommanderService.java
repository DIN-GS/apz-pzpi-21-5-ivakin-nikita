package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BattalionGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.BrigadeGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.LogisticCompanyDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.LogisticCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.BrigadeCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.BattalionGroupService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.BrigadeGroupService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.LogisticCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BrigadeCommanderService {

    @Autowired
    private final BrigadeCommanderRepository brigadeCommanderRepository;
    @Autowired
    private final BrigadeGroupService brigadeGroupService;
    @Autowired
    private final LogisticCompanyService logisticCompanyService;
    @Autowired
    private final BattalionGroupService battalionGroupService;
    @Autowired
    private final BattalionCommanderService battalionCommanderService;

    public BrigadeCommander getAuthenticatedBrigadeCommander() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String brigadeCommanderEmail = authentication.getName();
        return findBrigadeCommanderByEmail(brigadeCommanderEmail);
    }

    //TO DO: work with return statement
    public boolean createBrigade(BrigadeGroupDTO brigadeGroupDTO) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        brigadeGroupService.createBrigadeGroup(brigadeGroupDTO, brigadeCommander);
        BrigadeGroup brigadeGroup = brigadeGroupService.findBrigadeGroupByBrigadeCommander(brigadeCommander);
        brigadeCommander.setBrigadeGroupId(brigadeGroup);
        save(brigadeCommander);
        return true;
    }

    public boolean createLogisticCompany(LogisticCompanyDTO logisticCompanyDTO) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        return logisticCompanyService.createLogisticCompany(logisticCompanyDTO, brigadeCommander);
    }

    public boolean createBattalion(BattalionGroupDTO battalionGroupDTO) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        return battalionGroupService.createBattalionGroup(battalionGroupDTO, brigadeCommander);
    }

    public boolean assignLogisticCompanyCommander(LogisticCommander logisticCompanyCommander){

        return true;
    }

    public boolean assignBattalionCommander(Integer battalionCommanderId, Integer battalionGroupId) {
        BattalionCommander battalionCommander = battalionCommanderService.findBattalionCommanderById(battalionCommanderId);
        BattalionGroup battalionGroup = battalionGroupService.findBattalionGroupById(battalionGroupId);
        battalionGroup.setBattalionCommanderId(battalionCommander);
        battalionCommander.setBattalionGroup(battalionGroup);
        try {
            battalionCommanderService.save(battalionCommander);
            battalionGroupService.save(battalionGroup);
        } catch (Exception e) {
            log.info("Something went wrong in assigning BattalionCommander.");
            return false;
        }

        return true;
    }
    @Transactional
    public void save(BrigadeCommander brigadeCommander) {
        brigadeCommanderRepository.save(brigadeCommander);
    }

    /*BrigadeCommander findBrigadeCommanderById(Integer id) {
        Optional<BrigadeCommander> tempBrigCom = brigadeCommanderRepository.findBrigadeCommanderByBrigadeCommanderId(id);
        if (tempBrigCom.isPresent()) {
            return tempBrigCom.get();
        } else {
            log.info("Error brigade commander with id" + id + " doesn't exist.");
        }
        return null;
    }*/

   public BrigadeCommander findBrigadeCommanderByEmail(String email) {
        Optional<BrigadeCommander> tempBrigCom = brigadeCommanderRepository.findBrigadeCommanderByEmail(email);
        if (tempBrigCom.isPresent()) {
            return tempBrigCom.get();
        } else {
            log.info("Error brigade commander with email" + email  + " doesn't exist.");
        }
        return null;
   }

}
