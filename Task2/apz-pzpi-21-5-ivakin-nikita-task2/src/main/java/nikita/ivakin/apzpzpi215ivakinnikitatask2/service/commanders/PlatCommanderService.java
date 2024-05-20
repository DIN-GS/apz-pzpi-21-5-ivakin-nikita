package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.PlatCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.militaryGroups.PlatGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.PlatCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.PlatGroupService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests.ResourcesRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PlatCommanderService {

    @Autowired
    private final PlatCommanderRepository platCommanderRepository;
    @Autowired
    private final PlatGroupService platGroupService;
    @Autowired
    private final ResourcesRequestService resourcesRequestService;

    public PlatCommander getAuthenticatedPlatCommander() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String platCommanderEmail = authentication.getName();
        return findPlatCommanderByEmail(platCommanderEmail);
    }

    public PlatCommander findPlatCommanderById(Integer id){
        Optional<PlatCommander> tempPlatCom = platCommanderRepository.findPlatCommanderById(id);
        if (tempPlatCom.isPresent()) {
            return tempPlatCom.get();
        } else {
            log.info("Error plat commander with id" + id + " doesn't exist.");
        }
        return null;
    }

    public PlatCommander findPlatCommanderByEmail(String email) {
        Optional<PlatCommander> tempPlatCom = platCommanderRepository.findPlatCommanderByEmail(email);
        if (tempPlatCom.isPresent()) {
            return tempPlatCom.get();
        } else {
            log.info("Error plat commander with email" + email  + " doesn't exist.");
        }
        return null;
    }

    public PlatGroup getPlatGroup(){
        PlatCommander platCommander = getAuthenticatedPlatCommander();
        return platGroupService.findPlatGroupByPlatCommander(platCommander);
    }

    public PlatGroup updatePlatResources(PlatGroupDTO platGroupDTO) {
       return platGroupService.updatePlatResources(platGroupDTO);
    }

    public boolean askForResources(ResourcesRequestDTO resourcesRequestDTO) {

        return true;
    }

    @Transactional
    public void save(PlatCommander platCommander) {
        platCommanderRepository.save(platCommander);
    }


}
