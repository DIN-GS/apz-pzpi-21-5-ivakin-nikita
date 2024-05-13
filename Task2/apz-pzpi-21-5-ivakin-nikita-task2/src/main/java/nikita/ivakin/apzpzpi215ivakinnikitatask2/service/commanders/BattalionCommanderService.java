package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.BattalionCommanderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BattalionCommanderService {

    @Autowired
    private final BattalionCommanderRepository battalionCommanderRepository;

    @Transactional
    public void save(BattalionCommander battalionCommander) {
        battalionCommanderRepository.save(battalionCommander);
    }
}
