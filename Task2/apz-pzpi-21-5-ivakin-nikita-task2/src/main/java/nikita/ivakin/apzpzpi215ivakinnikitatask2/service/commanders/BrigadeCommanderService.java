package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.BrigadeCommanderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BrigadeCommanderService {

    @Autowired
    private final BrigadeCommanderRepository brigadeCommanderRepository;

    @Transactional
    public void save(BrigadeCommander brigadeCommander){
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

   /* BrigadeCommander findBrigadeCommanderByEmail(String email) {
        Optional<BrigadeCommander> tempBrigCom = brigadeCommanderRepository.findBrigadeCommanderByEmail(email);
        if (tempBrigCom.isPresent()) {
            return tempBrigCom.get();
        } else {
            log.info("Error brigade commander with email" + email  + " doesn't exist.");
        }
        return null;
    }*/

}
