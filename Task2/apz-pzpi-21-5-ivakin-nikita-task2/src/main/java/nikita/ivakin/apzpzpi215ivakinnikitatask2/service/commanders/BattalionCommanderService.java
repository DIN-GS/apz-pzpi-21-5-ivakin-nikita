package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.CompanyGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.CompanyCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.CompanyGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.BattalionCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.BattalionGroupService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.CompanyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BattalionCommanderService {

    @Autowired
    private final BattalionCommanderRepository battalionCommanderRepository;
    @Autowired
    private final BattalionGroupService battalionGroupService;
    @Autowired
    private final CompanyCommanderService companyCommanderService;
    @Autowired
    private final CompanyGroupService companyGroupService;



    public BattalionCommander getAuthenticatedBattalionCommander() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String battalionCommanderEmail = authentication.getName();
        return findBattalionCommanderByEmail(battalionCommanderEmail);
    }

    public boolean createCompany(CompanyGroupDTO companyGroupDTO) {
        BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
        return companyGroupService.createCompanyGroup(companyGroupDTO, battalionCommander);
    }

    public boolean assignCompanyCommander(Integer companyCommanderId, Integer companyGroupId){
        CompanyCommander companyCommander = companyCommanderService.findCompanyCommanderById(companyCommanderId);
        CompanyGroup companyGroup = companyGroupService.findCompanyGroupById(companyGroupId);
        companyGroup.setCompanyCommanderId(companyCommander);
        companyCommander.setCompanyGroup(companyGroup);
        try {
            companyCommanderService.save(companyCommander);
            companyGroupService.save(companyGroup);
        } catch (Exception e) {
            log.info("Something went wrong in assigning CompanyCommander.");
            return false;
        }

        return true;
    }

    public BattalionGroup findBattalionGroupById(Integer id) {
        return  battalionGroupService.findBattalionGroupById(id);
    }

    public BattalionCommander findBattalionCommanderByEmail(String email) {
        Optional<BattalionCommander> tempBatCom = battalionCommanderRepository.findBattalionCommanderByEmail(email);
        if (tempBatCom.isPresent()) {
            return tempBatCom.get();
        } else {
            log.info("Error battalion commander with email" + email  + " doesn't exist.");
        }
        return null;
    }

    public BattalionCommander findBattalionCommanderById(Integer id) {
        Optional<BattalionCommander> tempBatCom = battalionCommanderRepository.findBattalionCommanderById(id);
        if (tempBatCom.isPresent()) {
            return tempBatCom.get();
        } else {
            log.info("Error battalion commander with id" + id + " doesn't exist.");
        }
        return null;
    }

    public BattalionGroupService getBattalionGroupService() {
        return battalionGroupService;
    }

    @Transactional
    public void save(BattalionCommander battalionCommander) {
        battalionCommanderRepository.save(battalionCommander);
    }
}
