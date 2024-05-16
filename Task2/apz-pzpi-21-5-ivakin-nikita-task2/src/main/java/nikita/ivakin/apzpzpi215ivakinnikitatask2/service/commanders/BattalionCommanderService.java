package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.BattalionCommanderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BattalionCommanderService {

    @Autowired
    private final BattalionCommanderRepository battalionCommanderRepository;

    public BattalionCommander findBattalionCommanderById(Integer id) {
        Optional<BattalionCommander> tempBatCom = battalionCommanderRepository.findBattalionCommanderById(id);
        if (tempBatCom.isPresent()) {
            return tempBatCom.get();
        } else {
            log.info("Error battalion commander with id" + id + " doesn't exist.");
        }
        return null;
    }

    @Transactional
    public void save(BattalionCommander battalionCommander) {
        battalionCommanderRepository.save(battalionCommander);
    }
}
