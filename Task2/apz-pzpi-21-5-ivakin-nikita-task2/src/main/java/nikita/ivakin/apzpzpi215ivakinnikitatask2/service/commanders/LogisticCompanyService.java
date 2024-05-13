package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.commanders.LogisticCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.LogisticCommanderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class LogisticCompanyService {

    @Autowired
    private final LogisticCommanderRepository logisticCommanderRepository;

    @Transactional
    public void save(LogisticCommander logisticCommander){
        logisticCommanderRepository.save(logisticCommander);
    }
}
