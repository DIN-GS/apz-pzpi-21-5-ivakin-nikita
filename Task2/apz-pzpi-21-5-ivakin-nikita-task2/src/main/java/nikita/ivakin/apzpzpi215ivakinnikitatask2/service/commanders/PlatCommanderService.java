package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.CompanyCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.PlatCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.PlatCommanderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PlatCommanderService {

    @Autowired
    private final PlatCommanderRepository platCommanderRepository;

    public PlatCommander findPlatCommanderById(Integer id){
        Optional<PlatCommander> tempPlatCom = platCommanderRepository.findPlatCommanderById(id);
        if (tempPlatCom.isPresent()) {
            return tempPlatCom.get();
        } else {
            log.info("Error plat commander with id" + id + " doesn't exist.");
        }
        return null;
    }
    @Transactional
    public void save(PlatCommander platCommander) {
        platCommanderRepository.save(platCommander);
    }
}
