package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.PlatCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.PlatCommanderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PlatCommanderService {

    @Autowired
    private final PlatCommanderRepository platCommanderRepository;

    @Transactional
    public void save(PlatCommander platCommander) {
        platCommanderRepository.save(platCommander);
    }
}
